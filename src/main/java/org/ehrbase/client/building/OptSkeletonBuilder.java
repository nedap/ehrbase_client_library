/*
 * Modifications copyright (C) 2019 Stefan Spiska (Vitasystems GmbH) and Hannover Medical School,
 *
 * This file is part of Project EHRbase
 *
 * Copyright (c) 2015 Christian Chevalley
 * This file is part of Project Ethercis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.client.building;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nedap.archie.aom.OperationalTemplate;
import com.nedap.archie.creation.ExampleJsonInstanceGenerator;
import com.nedap.archie.json.JacksonUtil;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import org.openehr.referencemodels.BuiltinReferenceModels;

import java.util.Map;

public class OptSkeletonBuilder {

    private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();



    /**
     * Generate empty Rm from template
     *
     * @param opt
     * @return
     * @throws Exception
     */
    public RMObject generate(OperationalTemplate opt, String language) {
        //TODO: probably need to move this entire thing here to make it generate the exactly right output :)
        ExampleJsonInstanceGenerator exampleJsonInstanceGenerator = new ExampleJsonInstanceGenerator(BuiltinReferenceModels.getMetaModels(), language);
        Map<String, Object> jsonMap =  exampleJsonInstanceGenerator.generate(opt);
        String json = null;
        try {
            json = JacksonUtil.getObjectMapper().writeValueAsString(jsonMap);
            return JacksonUtil.getObjectMapper().readValue(json, RMObject.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
