package yapion.serializing.serializer.other;

import yapion.annotations.deserialize.YAPIONLoadExclude;
import yapion.annotations.serialize.YAPIONSaveExclude;
import yapion.hierarchy.YAPIONAny;
import yapion.hierarchy.types.YAPIONValue;
import yapion.serializing.InternalSerializer;
import yapion.serializing.YAPIONDeserializer;
import yapion.serializing.YAPIONSerializer;
import yapion.serializing.serializer.SerializerImplementation;

@YAPIONSaveExclude(context = "*")
@YAPIONLoadExclude(context = "*")
@SerializerImplementation
public class RunnableSerializer implements InternalSerializer<Runnable> {

    @Override
    public String type() {
        return "java.lang.Runnable";
    }

    @Override
    public YAPIONAny serialize(Runnable object, YAPIONSerializer yapionSerializer) {
        return new YAPIONValue<>(null);
    }

    @Override
    public Runnable deserialize(YAPIONAny yapionAny, YAPIONDeserializer yapionDeserializer) {
        return null;
    }

}
