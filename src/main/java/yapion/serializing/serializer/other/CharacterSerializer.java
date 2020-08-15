// SPDX-License-Identifier: Apache-2.0
// YAPION
// Copyright (C) 2019,2020 yoyosource

package yapion.serializing.serializer.other;

import yapion.annotations.YAPIONLoadExclude;
import yapion.annotations.YAPIONSaveExclude;
import yapion.hierarchy.YAPIONAny;
import yapion.hierarchy.types.YAPIONValue;
import yapion.serializing.Serializer;
import yapion.serializing.YAPIONDeserializer;
import yapion.serializing.YAPIONSerializer;

import java.lang.reflect.Field;

@YAPIONSaveExclude(context = "*")
@YAPIONLoadExclude(context = "*")
public class CharacterSerializer implements Serializer<Character> {

    @Override
    public String type() {
        return "java.lang.Character";
    }

    @Override
    public String primitiveType() {
        return "char";
    }

    @Override
    public YAPIONAny serialize(Character object, YAPIONSerializer yapionSerializer) {
        return new YAPIONValue<>(object);
    }

    @Override
    public Character deserialize(YAPIONAny yapionAny, YAPIONDeserializer yapionDeserializer, Field field) {
        return ((YAPIONValue<Character>) yapionAny).get();
    }
}