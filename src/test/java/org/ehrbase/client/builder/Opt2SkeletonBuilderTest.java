package org.ehrbase.client.builder;

import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.OperationalTemplate;
import com.nedap.archie.flattener.Flattener;
import com.nedap.archie.flattener.InMemoryFullArchetypeRepository;
import com.nedap.archie.json.JacksonUtil;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.building.Opt2SkeletonBuilder;
import org.ehrbase.client.classgenerator.ClassGenerator;
import org.ehrbase.client.classgenerator.ClassGeneratorResult;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.OpenEHREHROBSERVATIONBloodPressureV110Observation;
import org.ehrbase.client.flattener.RMObjectFlattener;
import org.ehrbase.client.flattener.RmObjectUnflattener;
import org.ehrbase.client.templateprovider.TemplateProvider;
import org.junit.Test;
import org.openehr.referencemodels.BuiltinReferenceModels;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class Opt2SkeletonBuilderTest {

    @Test
    public void buildEmptyBloodPressure() throws Exception {
        try (InputStream in = getClass().getResourceAsStream("/openEHR-EHR-OBSERVATION.blood_pressure.v1.1.0.adls")) {
            ADLParser parser = new ADLParser(BuiltinReferenceModels.getMetaModels());
            Archetype parsed = parser.parse(in);
            Flattener flattener = new Flattener(new InMemoryFullArchetypeRepository(), BuiltinReferenceModels.getMetaModels()).createOperationalTemplate(true);
            OperationalTemplate opt = (OperationalTemplate) flattener.flatten(parsed);
            Opt2SkeletonBuilder builder = new Opt2SkeletonBuilder("en");
            Locatable emptyRMObject = builder.createEmptyRMObject(opt);
            System.out.println(emptyRMObject);

            RMObjectFlattener rmObjectFlattener = new RMObjectFlattener();
            OpenEHREHROBSERVATIONBloodPressureV110Observation flatObject = rmObjectFlattener.flatten(emptyRMObject, OpenEHREHROBSERVATIONBloodPressureV110Observation.class);
            System.out.println(flatObject);
            flatObject.setSystolicMagnitude(120.0d);
            flatObject.setSystolicUnits("mm[Hg]");//only one possible, should have been prefilled!
            flatObject.setDiastolicMagnitude(80.0d);
            flatObject.setSystolicUnits("mm[Hg]");//only one possible, should have been prefilled!
            RmObjectUnflattener unflattener = new RmObjectUnflattener(new TemplateProvider() {
                @Override
                public Optional<OperationalTemplate> find(String templateId) {
                    return Optional.of(opt);
                }
            }, "en");
            Locatable unflattened = (Locatable) unflattener.unflatten(flatObject);
            System.out.println(JacksonUtil.getObjectMapper().writeValueAsString(unflattened));
            assertEquals(120.0d, (Double) unflattened.itemAtPath("/data[id2]/events[id1043]/data[id1071]/items[id5]/value/magnitude"), 0.0001d);
            assertEquals("mm[Hg]", unflattened.itemAtPath("/data[id2]/events[id1043]/data[id1071]/items[id5]/value/units"));

        }
    }
}
