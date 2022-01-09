/*
 * Copyright 2019,2020,2021 yoyosource
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yapion.serializing.serializer.object.function;

import yapion.annotations.api.SerializerImplementation;
import yapion.serializing.serializer.FinalInternalSerializer;

import java.util.function.ObjDoubleConsumer;

@SerializerImplementation(since = "0.26.0")
public class ObjDoubleConsumerSerializer extends AbstractFunctionSerializer<ObjDoubleConsumer> implements FinalInternalSerializer<ObjDoubleConsumer> {

    @Override
    public void init() {
        clazz = ObjDoubleConsumer.class;
    }
}
