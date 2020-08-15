// SPDX-License-Identifier: Apache-2.0
// YAPION
// Copyright (C) 2019,2020 yoyosource

package yapion.serializing.serializer.object;

import yapion.annotations.YAPIONLoadExclude;
import yapion.annotations.YAPIONSaveExclude;
import yapion.hierarchy.YAPIONAny;
import yapion.hierarchy.types.YAPIONMap;
import yapion.serializing.Serializer;
import yapion.serializing.YAPIONDeserializer;
import yapion.serializing.YAPIONSerializer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@YAPIONSaveExclude(context = "*")
@YAPIONLoadExclude(context = "*")
public class MapSerializerHash implements Serializer<HashMap> {

    @Override
    public String type() {
        return "java.util.HashMap";
    }

    @Override
    public YAPIONAny serialize(HashMap object, YAPIONSerializer yapionSerializer) {
        YAPIONMap yapionMap = new YAPIONMap();
        for (Object obj : object.entrySet()) {
            Map.Entry entry = (Map.Entry)obj;
            yapionMap.add(yapionSerializer.parse(entry.getKey(), yapionSerializer), yapionSerializer.parse(entry.getValue(), yapionSerializer));
        }
        return yapionMap;
    }

    @Override
    public HashMap deserialize(YAPIONAny yapionAny, YAPIONDeserializer yapionDeserializer, Field field) {
        YAPIONMap yapionMap = (YAPIONMap)yapionAny;
        HashMap<Object, Object> map = new HashMap();
        for (YAPIONAny key : yapionMap.getKeys()) {
            map.put(yapionDeserializer.parse(key, yapionDeserializer, field), yapionDeserializer.parse(yapionMap.get(key), yapionDeserializer, field));
        }
        return map;
    }

}