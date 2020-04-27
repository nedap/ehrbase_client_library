package org.ehrbase.client.builder;

import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.OperationalTemplate;
import com.nedap.archie.flattener.Flattener;
import com.nedap.archie.flattener.InMemoryFullArchetypeRepository;
import com.nedap.archie.rm.archetyped.Locatable;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.building.Opt2SkeletonBuilder;
import org.ehrbase.client.classgenerator.ClassGenerator;
import org.ehrbase.client.classgenerator.ClassGeneratorResult;
import org.junit.Test;
import org.openehr.referencemodels.BuiltinReferenceModels;

import java.io.IOException;
import java.io.InputStream;

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


        }
    }
}
