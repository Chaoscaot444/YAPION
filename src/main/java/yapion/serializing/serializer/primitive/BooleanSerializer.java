// SPDX-License-Identifier: Apache-2.0
// YAPION
// Copyright (C) 2019,2020,2021 yoyosource

package yapion.serializing.serializer.primitive;

import yapion.hierarchy.api.groups.YAPIONAnyType;
import yapion.hierarchy.types.YAPIONValue;
import yapion.serializing.InternalSerializer;
import yapion.serializing.data.DeserializeData;
import yapion.serializing.data.SerializeData;
import yapion.serializing.serializer.SerializerImplementation;

@SerializerImplementation(since = "0.11.0")
public class BooleanSerializer implements InternalSerializer<Boolean> {

    @Override
    public String type() {
        return "java.lang.Boolean";
    }

    @Override
    public String primitiveType() {
        return "boolean";
    }

    @Override
    public YAPIONAnyType serialize(SerializeData<Boolean> serializeData) {
        return new YAPIONValue<>(serializeData.object);
    }

    @Override
    public Boolean deserialize(DeserializeData<? extends YAPIONAnyType> deserializeData) {
        return ((YAPIONValue<Boolean>) deserializeData.object).get();
    }
}