package org.ehrbase.client.building;

import com.google.common.collect.Lists;
import com.nedap.archie.ArchieLanguageConfiguration;
import com.nedap.archie.adlparser.modelconstraints.RMConstraintImposer;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.CArchetypeRoot;
import com.nedap.archie.aom.CAttribute;
import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.aom.CObject;
import com.nedap.archie.base.MultiplicityInterval;
import com.nedap.archie.creation.RMObjectCreator;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.support.identification.ArchetypeID;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.ModelInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.ehrbase.client.building.rmobjektskeletonbuilder.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Opt2SkeletonBuilder {

    private static final String RM_VERSION = "1.0.4";
    private final String language;
    private RMConstraintImposer defaultAttributeCreator;

    public Opt2SkeletonBuilder(String language) {
        this.language = language;
        defaultAttributeCreator = new RMConstraintImposer();
    }

    public Locatable createEmptyRMObject(Archetype archetype) {
        ArchieLanguageConfiguration.setThreadLocalDescriptiongAndMeaningLanguage(language);

        RMObjectCreator creator = new RMObjectCreator(ArchieRMInfoLookup.getInstance());
        RMObject rmObject = constructEmptyRMObject(ArchieRMInfoLookup.getInstance(), creator, archetype.getDefinition());
        Locatable locatable = setRootArchetypeDetails(archetype, (Locatable) rmObject);
        return locatable;
    }

    private Locatable setRootArchetypeDetails(Archetype archetype, Locatable rmObject) {
        Locatable locatable = rmObject;
        Archetyped details = new Archetyped(new ArchetypeID(archetype.getArchetypeId().getFullId()), RM_VERSION);
        locatable.setArchetypeDetails(details);
        return locatable;
    }


    private RMObject constructEmptyRMObject(ModelInfoLookup lookup, RMObjectCreator creator, CObject object) {
        RMObject result = creator.create(object);
        for (CAttribute attribute : object.getAttributes()) {
            if(isProhibited(attribute)) { //just if someone forgot to remove these from the OPT :)
                continue;
            }
            List<Object> children = new ArrayList<>();
            for (CObject childConstraint : attribute.getChildren()) {
                if (childConstraint instanceof CComplexObject) {
                    createComplexObject(lookup, creator, children, childConstraint);
                } else {
                    createPrimitiveObject(lookup, creator, children, childConstraint);
                }
            }
            setAttributeValue(lookup, creator, result, attribute, children);
        }

        RMTypeInfo typeInfo = lookup.getTypeInfo(object.getRmTypeName());
        addDefaultAttributes(lookup, creator, result, typeInfo);

        return result;

    }

    private void setAttributeValue(ModelInfoLookup lookup, RMObjectCreator creator, RMObject result, CAttribute attribute, List<Object> children) {
        if (!children.isEmpty()) {
            //this is not BMM, but access to the actual RM implementation because that is what we need here
            RMAttributeInfo attributeInfo = lookup.getAttributeInfo(result.getClass(), attribute.getRmAttributeName());
            if (attributeInfo != null) {
                if (attributeInfo.isMultipleValued()) {
                    creator.set(result, attribute.getRmAttributeName(), children);
                } else if (!children.isEmpty()) {
                    //set the first possible result in case of multiple children for a single valued value
                    try {
                        creator.set(result, attribute.getRmAttributeName(), Lists.newArrayList(children.get(0)));
                    } catch (RuntimeException e) {
                        throw e;
                    }
                }
            }
        }
    }

    private void addDefaultAttributes(ModelInfoLookup lookup, RMObjectCreator creator, RMObject result,
                                      RMTypeInfo typeInfo) {
        Map<String, RMAttributeInfo> attributes = typeInfo.getAttributes();
        for(RMAttributeInfo attributeInfo:attributes.values()) {
            try {
                if(!attributeInfo.isNullable()
                        && attributeInfo.getField() != null
                        && attributeInfo.getGetMethod() != null
                        && attributeInfo.getGetMethod().invoke(result) == null) { //reflection speak for required, but not present
                    CAttribute defaultAttribute = defaultAttributeCreator.getDefaultAttribute(typeInfo.getRmName(), attributeInfo.getRmName());
                    Object defaultValue = createDefaultValue(attributeInfo);
                    if(defaultValue != null) {
                        creator.set(result, defaultAttribute.getRmAttributeName(), Lists.newArrayList(defaultValue));
                        RMTypeInfo nextInfo = lookup.getTypeInfo(defaultValue.getClass());
                        if(defaultValue instanceof RMObject && nextInfo != null) {
                            //recursive look since even this object can have required values.
                            addDefaultAttributes(lookup, creator, (RMObject) defaultValue, nextInfo);
                        }
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPrimitiveObject(ModelInfoLookup lookup, RMObjectCreator creator, List<Object> children, CObject childConstraint) {

       //nothing for now
    }

    private void createComplexObject(ModelInfoLookup lookup, RMObjectCreator creator, List<Object> children, CObject childConstraint) {
        int numberOfInstances = determineNumberOfInstances(lookup, childConstraint);
        for(int i = 0; i < numberOfInstances; i++ ) {
            //in case of abstract classes used in the archetype, this will pick a non-abstract descendant class
            RMObject childObject = constructEmptyRMObject(lookup, creator, childConstraint);
            setArchetypeDetails(childConstraint, childObject);
            children.add(childObject);
        }
    }

    private Object createDefaultValue(RMAttributeInfo attributeInfo) {
        try {
            return attributeInfo.getType().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            //TODO: log
            return null;
        }
    }

    private int determineNumberOfInstances(ModelInfoLookup lookup, CObject childConstraint) {
        int numberOfInstances = 1;
        MultiplicityInterval occurrences = childConstraint.effectiveOccurrences(lookup::referenceModelPropMultiplicity);
        if(occurrences.isProhibited()) {
            numberOfInstances = 0;
        } else {
            numberOfInstances = occurrences.getLower();
            if(!occurrences.isLowerIncluded()) {
                numberOfInstances++;
            }
            if(numberOfInstances == 0 && occurrences.has(1)) {
                numberOfInstances = 1;
            }
            //TODO: if cardinality prohibits this, do something here :)
        }
        return numberOfInstances;
    }

    private void setArchetypeDetails(CObject childConstraint, RMObject childObject) {
        if(childConstraint instanceof CArchetypeRoot && childObject instanceof Locatable) {
            Locatable child = (Locatable) childObject;
            Archetyped details = new Archetyped(new ArchetypeID(((CArchetypeRoot) childConstraint).getArchetypeRef()), RM_VERSION);
            child.setArchetypeDetails(details);
        }
    }

    private boolean isProhibited(CAttribute attribute) {
        return (attribute.getExistence() != null && attribute.getExistence().isProhibited()) ||
            (attribute.getCardinality() != null && attribute.getCardinality().getInterval() != null && attribute.getCardinality().getInterval().isProhibited());
    }
}
