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

package yapion.serializing.serializer.object.atomic;

import yapion.annotations.api.SerializerImplementation;
import yapion.hierarchy.api.groups.YAPIONAnyType;
import yapion.hierarchy.types.YAPIONArray;
import yapion.hierarchy.types.YAPIONObject;
import yapion.serializing.InternalSerializer;
import yapion.serializing.data.DeserializeData;
import yapion.serializing.data.SerializeData;

import java.util.concurrent.atomic.AtomicReferenceArray;

@SerializerImplementation(since = "0.20.0")
public class AtomicReferenceArraySerializer implements InternalSerializer<AtomicReferenceArray<?>> {

    @Override
    public Class<?> type() {
        return AtomicReferenceArray.class;
    }

    @Override
    public YAPIONAnyType serialize(SerializeData<AtomicReferenceArray<?>> serializeData) {
        YAPIONObject yapionObject = new YAPIONObject(type());
        yapionObject.add("length", serializeData.object.length());

        Object[] objects = new Object[serializeData.object.length()];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = serializeData.object.get(i);
        }
        yapionObject.add("values", serializeData.serialize(objects));
        return yapionObject;
    }

    @Override
    public AtomicReferenceArray<?> deserialize(DeserializeData<? extends YAPIONAnyType> deserializeData) {
        YAPIONObject yapionObject = (YAPIONObject) deserializeData.object;
        int length = yapionObject.getValue("length", 0).get();
        YAPIONArray yapionArray = yapionObject.getArray("values");
        Object[] objects = new Object[length];
        for (int i = 0; i < length; i++) {
            objects[i] = deserializeData.deserialize(yapionArray.getYAPIONAnyType(i));
        }
        return new AtomicReferenceArray<>(objects);
    }

}
