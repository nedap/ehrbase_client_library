/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.introspect;

import com.nedap.archie.ArchieLanguageConfiguration;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.ArchetypeSlot;
import com.nedap.archie.aom.CArchetypeRoot;
import com.nedap.archie.aom.CAttribute;
import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.aom.CObject;
import com.nedap.archie.aom.OperationalTemplate;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.aom.terminology.TerminologyCodeWithArchetypeTerm;
import com.nedap.archie.aom.utils.AOMUtils;
import com.nedap.archie.base.MultiplicityInterval;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.MetaModels;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flatpath.FlatPath;
import org.ehrbase.client.introspect.config.RmIntrospectConfig;
import org.ehrbase.client.introspect.node.ArchetypeNode;
import org.ehrbase.client.introspect.node.ChoiceNode;
import org.ehrbase.client.introspect.node.EndNode;
import org.ehrbase.client.introspect.node.EntityNode;
import org.ehrbase.client.introspect.node.Node;
import org.ehrbase.client.introspect.node.SlotNode;
import org.ehrbase.client.terminology.TermDefinition;
import org.ehrbase.client.terminology.TerminologyProvider;
import org.ehrbase.client.terminology.ValueSet;
import org.ehrbase.ehr.encode.wrappers.SnakeCase;
import org.openehr.referencemodels.BuiltinReferenceModels;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.ehrbase.client.terminology.TerminologyProvider.OPENEHR;
import static org.ehrbase.client.terminology.ValueSet.EMPTY_VALUE_SET;
import static org.ehrbase.client.terminology.ValueSet.LOCAL;

public class TemplateIntrospect {

    private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    public static final String PATH_DIVIDER = "/";
    public static final String TERM_DIVIDER = "/";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Map<Class, RmIntrospectConfig> configMap;
    private final OperationalTemplate operationaltemplate;
    private final ArchetypeNode root;
    private String language = "en";//TODO: make configurable

    private final MetaModels metaModels;



    public TemplateIntrospect(OperationalTemplate operationaltemplate, String language) {

        this.operationaltemplate = operationaltemplate;
        metaModels = BuiltinReferenceModels.getMetaModels();
        if (metaModels == null) {
            //SOMEONE did something ugly
            throw new RuntimeException("NO METAMODELS!");
        }
        metaModels.selectModel(operationaltemplate);
        configMap = buildConfigMap();
        // constructor is doing all the work?
        root = buildNodeMap();

        this.language = language;

    }

    public static Map<Class, RmIntrospectConfig> buildConfigMap() {

        Reflections reflections = new Reflections(RmIntrospectConfig.class.getPackage().getName());
        Set<Class<? extends RmIntrospectConfig>> configs = reflections.getSubTypesOf(RmIntrospectConfig.class);

        return configs.stream()
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .map(c -> {
                    try {
                        return c.getConstructor().newInstance();
                    } catch (Exception e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }).collect(Collectors.toMap(RmIntrospectConfig::getRMClass, c -> c));
    }

    private ArchetypeNode buildNodeMap() {
        CComplexObject definition = operationaltemplate.getDefinition();
        ArchetypeTerminology terminology = operationaltemplate.getTerminology();
        return new ArchetypeNode(
                getText(definition, terminology),
                definition.getNodeId(),
                handleCComplexObject(definition, "", terminology, ""), false, definition.getRmTypeName());

    }

    private ArchetypeNode handleCARCHETYPEROOT(CArchetypeRoot definition, String name, boolean multi) {

        // Keep term definition to map
        Archetype archetype = definition.getArchetype();
        ArchetypeTerminology terminology = archetype.getTerminology(definition);

        ArchetypeTerm term = archetype.getTerm(definition, language);
        return new ArchetypeNode(
                term != null ? term.getText() : "unknown term for " + definition.getNodeId(),
                definition.getNodeId(),
                    handleCComplexObject(definition, "", terminology, ""), multi, definition.getRmTypeName());
    }

    private Map<String, Node> handleCComplexObject(CComplexObject ccomplexobject, String path, ArchetypeTerminology termDef, String term) {

        HashMap<String, Node> localNodeMap = new HashMap<>();
        log.trace("RmTyp: {}", ccomplexobject.getRmTypeName());
        Class rmClass = RM_INFO_LOOKUP.getClass(ccomplexobject.getRmTypeName());

        if (Pathable.class.isAssignableFrom(rmClass)) {
            //fields that must be added even if not in the template
            //will be replaced later on
            Map<String, Node> nonTemplateFields = handleNonTemplateFields(rmClass, path);


            List<CAttribute> cattributes = ccomplexobject.getAttributes();
            if (!cattributes.isEmpty()) {
                ListValuedMap<String, Node> multiValuedMap = new ArrayListValuedHashMap<>();

                for (CAttribute cattribute : cattributes) {
                    String pathLoop = path + PATH_DIVIDER + cattribute.getRmAttributeName();
                    log.trace("Path: {}", pathLoop);
                    if (
                            (Event.class.isAssignableFrom(rmClass) && pathLoop.contains("offset")) // event.offset is a calculated value
                                    || pathLoop.equals("/category") || pathLoop.equals("/name") // set from template
                    ) {
                        continue;
                    }

                    for (CObject cobject : cattribute.getChildren()) {
                        multiValuedMap.putAll(handleCObject(cobject, pathLoop, termDef, term));
                    }

                }
                for(Map.Entry<String, Collection<Node>> entry:multiValuedMap.asMap().entrySet()) {
                    String key = entry.getKey();
                    Collection<Node> value = entry.getValue();
                    if (value.size() == 1) {
                        localNodeMap.put(key, value.iterator().next());
                    } else {
                        localNodeMap.put(key,
                                new ChoiceNode(
                                        value.iterator().next().getName(),
                                        new ArrayList<>(value),
                                        value.stream().filter(n -> EntityNode.class.isAssignableFrom(n.getClass())).map(n -> (EntityNode) n).anyMatch(EntityNode::isMulti))
                        );
                    }
                }
                if (History.class.isAssignableFrom(rmClass)) {
                    Map<String, List<Map.Entry<String, Node>>> collect = localNodeMap.entrySet().stream().filter(e -> e.getKey().contains("/data[at0002]/event")).collect(Collectors.groupingBy(e -> new FlatPath(e.getKey()).getChild().getAtCode()));
                    if (collect.keySet().size() > 1) {
                        for (Map.Entry<String, List<Map.Entry<String, Node>>> entry : collect.entrySet()) {
                            if (entry.getValue().size() > 1) {
                                EntityNode entityNode = new EntityNode(term + TERM_DIVIDER + termDef.getTermDefinition(language, entry.getKey()).getText(),
                                        false,
                                        "EVENT",
                                        entry.getValue().stream().collect(Collectors.toMap(k -> k.getKey().replace("/data[at0002]/events" + "[" + entry.getKey() + "]", ""),
                                        (Function<Map.Entry<String, Node>, Node>) Map.Entry::getValue)));
                                localNodeMap.put("/data[at0002]/events" + "[" + entry.getKey() + "]", entityNode);
                                entry.getValue().forEach(e -> localNodeMap.remove(e.getKey()));
                            }
                        }
                    }

                }
            }
            if (Composition.class.isAssignableFrom(rmClass)) {
                if (!localNodeMap.containsKey("/context/end_time")) {
                    localNodeMap.put("/context/end_time", new EndNode(DvDateTime.class, "end_time"));
                }
                if (!localNodeMap.containsKey("/context/participations")) {
                    localNodeMap.put("/context/participations", new EndNode(Participation.class, "participations", EMPTY_VALUE_SET, true));
                }
                if (!localNodeMap.containsKey("/context/health_care_facility")) {
                    localNodeMap.put("/context/health_care_facility", new EndNode(PartyIdentified.class, "healthCareFacility"));
                }
                if (!localNodeMap.containsKey("/context/start_time")) {
                    localNodeMap.put("/context/start_time", new EndNode(DvDateTime.class, "start_time"));
                }
                if (!localNodeMap.containsKey("/context/location")) {
                    localNodeMap.put("/context/location", new EndNode(String.class, "location"));
                }
                if (!localNodeMap.containsKey("/context/setting")) {
                    localNodeMap.put("/context/setting", new EndNode(DvCodedText.class, "setting", TerminologyProvider.findOpenEhrValueSet(OPENEHR, "setting")));
                }
            }

            //add only those default fields that aren't in the template to the tree of nodes
            Map<String, Node> nonTemplateNodesToAdd = new LinkedHashMap<>();
            for(String nonTemplatePath:nonTemplateFields.keySet()) {
                if(localNodeMap.keySet().stream().filter(p -> p.startsWith(nonTemplatePath)).findAny().isEmpty()) {
                    //this hasn't been added by the archetype. Add
                    //TODO: add CAttribute name to the EndNode? Better than this!
                    nonTemplateNodesToAdd.put(nonTemplatePath, nonTemplateFields.get(nonTemplatePath));
                }
            }
            localNodeMap.putAll(nonTemplateNodesToAdd);
        } else {
            //TODO: WHAT does this code do and why?!
            ValueSet termDefinitionSet = ccomplexobject.getAttributes().stream()
                    .flatMap(c -> (c.getChildren().stream()))
                    .map(d -> buildTermSet(d, termDef))
                    .findAny()
                    .orElse(EMPTY_VALUE_SET);
            localNodeMap.put(path, new EndNode(findJavaClass(ccomplexobject.getRmTypeName()), term, termDefinitionSet));
        }
        return localNodeMap;
    }

    private Map<String, Node> handleCObject(CObject cobject, String
            path, ArchetypeTerminology termDef, String term) {

        MultiplicityInterval occurrences = cobject.effectiveOccurrences(metaModels::referenceModelPropMultiplicity);
        boolean multi = occurrences.isUpperUnbounded() || (occurrences.getUpper() > 1  && occurrences.isUpperIncluded()) || (occurrences.getUpper() > 2) ;

        if (cobject instanceof CArchetypeRoot && !((CArchetypeRoot) cobject).getArchetypeRef().isEmpty()) {
            path = path + "[" + ((CArchetypeRoot) cobject).getArchetypeRef() + "]";
            log.trace("Path: {}", path);

            String text = getText(cobject, termDef);
            if (!cobject.getNodeId().isEmpty()&& text != null && !text.isEmpty()) {
                term = term + TERM_DIVIDER + text;
            }

            return Collections.singletonMap(path, handleCARCHETYPEROOT((CArchetypeRoot) cobject, term, multi));

        } else if (cobject instanceof CComplexObject && multi) {
            path = path + "[" + cobject.getNodeId() + "]";
            log.trace("Path: {}", path);
            return Collections.singletonMap(path, handleEntity((CComplexObject) cobject, term, termDef, multi));

        } else if (cobject instanceof CComplexObject) {

            if (cobject.getNodeId() != null && !cobject.getNodeId().isEmpty()) {
                path = path + "[" + cobject.getNodeId() + "]";
                log.trace("Path: {}", path);
                String text = getText(cobject, termDef);
                if (text != null && !text.isEmpty()) {
                    term = term + TERM_DIVIDER + text;
                }
            }

            return handleCComplexObject((CComplexObject) cobject, path, termDef, term);

        } else if (cobject instanceof ArchetypeSlot) {
            if (!cobject.getNodeId().isEmpty()) {
                path = path + "[" + cobject.getNodeId() + "]";
                log.trace("Path: {}", path);
                String text = getText(cobject, termDef);
                if (text != null && !text.isEmpty()) {
                    term = term + TERM_DIVIDER + text;
                }
            }
            return Collections.singletonMap(path, new SlotNode(findJavaClass(cobject.getRmTypeName()), term, new ValueSet(LOCAL, Collections.emptySet()), multi));

        } else {

            return Collections.singletonMap(path, new EndNode(findJavaClass(cobject.getRmTypeName()), term, buildTermSet(cobject, termDef)));
        }
    }

    private ValueSet buildTermSet(CObject cobject, ArchetypeTerminology terminology) {
        final ValueSet valueSet;
        if (cobject instanceof CTerminologyCode) {
//TODO: rewrite
            CTerminologyCode ccodephrase = (CTerminologyCode) cobject;
            List<TerminologyCodeWithArchetypeTerm> terms = ccodephrase.getTerms();
            if(!ccodephrase.getConstraint().isEmpty() && AOMUtils.isValueSetCode(ccodephrase.getConstraint().get(0))) {
                Set<TermDefinition> converterdTerms = terms.stream().map(t -> new TermDefinition(t.getCode(), t.getTerm().getText(), t.getTerm().getDescription())).collect(Collectors.toSet());
                valueSet = new ValueSet("local", converterdTerms);
            } else if (!terms.isEmpty()) {
                Set<TermDefinition> converterdTerms = terms.stream().map(t -> new TermDefinition(t.getCode(), t.getTerm().getText(), t.getTerm().getDescription())).collect(Collectors.toSet());
                valueSet = new ValueSet("local", converterdTerms);
            } else {
                valueSet = EMPTY_VALUE_SET;
            }
        } else {
            valueSet = EMPTY_VALUE_SET;
        }
        return valueSet;
    }


    private Class findJavaClass(String rmName) {
        switch (rmName) {
            case "STRING":
                return String.class;
            case "INTEGER":
            case "INTEGER64":
                return Long.class;
            case "BOOLEAN":
                return Boolean.class;
            case "REAL":
            case "DOUBLE":
                return Double.class;
            default:
                return RM_INFO_LOOKUP.getClass(rmName);
        }
    }

    private Node handleEntity(CComplexObject cobject, String name, ArchetypeTerminology termDef,
                              boolean multi) {
        Class rmClass = RM_INFO_LOOKUP.getClass(cobject.getRmTypeName());
        if (Event.class.isAssignableFrom(rmClass)) {

            cobject.setRmTypeName("POINT_EVENT");
            EntityNode pointNode = new EntityNode(name + TERM_DIVIDER + getText(cobject, termDef), false, cobject.getRmTypeName(), handleCComplexObject(cobject, "", termDef, ""));
            cobject.setRmTypeName("INTERVAL_EVENT");
            EntityNode intervalNode = new EntityNode(name + TERM_DIVIDER + getText(cobject, termDef), false, cobject.getRmTypeName(), handleCComplexObject(cobject, "", termDef, ""));
            return new ChoiceNode(name + TERM_DIVIDER + getText(cobject, termDef), Arrays.asList(pointNode, intervalNode), multi);
        } else {
            return new EntityNode(name + TERM_DIVIDER +
                    getText(cobject, termDef), multi, cobject.getRmTypeName(),
                    handleCComplexObject(cobject, "", termDef, ""));
        }
    }

    private String getText(CObject cobject, ArchetypeTerminology termDef) {
        return Optional.ofNullable(termDef.getTermDefinition(language, cobject.getNodeId())).map(ArchetypeTerm::getText).orElse("");
    }

    private Map<String, Node> handleNonTemplateFields(Class clazz, String path) {
        RmIntrospectConfig introspectConfig = configMap.get(clazz);
        if (introspectConfig != null) {
            HashMap<String, Node> localNodeMap = new HashMap<>();
            Arrays.stream(FieldUtils.getAllFields(clazz))
                    .filter(f -> introspectConfig.getNonTemplateFields().contains(f.getName()))
                    .forEach(f -> {
                        String snakeName = new SnakeCase(f.getName()).camelToSnake();
                        String localPath = path + PATH_DIVIDER + snakeName;
                        localNodeMap.put(localPath, new EndNode(unwrap(f), snakeName, introspectConfig.findExternalValueSet(f.getName()), List.class.isAssignableFrom(f.getType())));
                    });
            return localNodeMap;
        } else {
            log.debug("No RmIntrospectConfig for {}", clazz);
            return Collections.emptyMap();
        }
    }

    public Class unwrap(Field field) {
        if (List.class.isAssignableFrom(field.getType())) {
            Type actualTypeArgument = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

            return ReflectionUtils.forName(actualTypeArgument.getTypeName(), this.getClass().getClassLoader());
        } else {
            return field.getType();
        }
    }

    public ArchetypeNode getRoot() {
        return root;
    }
}
