package yapion.serializing.serializer.object.throwable;

import yapion.annotations.deserialize.YAPIONLoadExclude;
import yapion.annotations.serialize.YAPIONSaveExclude;
import yapion.exceptions.YAPIONException;
import yapion.hierarchy.output.StringPrettifiedOutput;
import yapion.hierarchy.typegroups.YAPIONAnyType;
import yapion.hierarchy.types.YAPIONArray;
import yapion.hierarchy.types.YAPIONObject;
import yapion.serializing.InternalSerializer;
import yapion.serializing.YAPIONDeserializer;
import yapion.serializing.YAPIONSerializer;
import yapion.serializing.data.DeserializeData;
import yapion.serializing.data.SerializeData;
import yapion.serializing.serializer.SerializerImplementation;
import yapion.utils.ReflectionsUtils;

import static yapion.utils.IdentifierUtils.EXCEPTION_IDENTIFIER;
import static yapion.utils.IdentifierUtils.TYPE_IDENTIFIER;

@YAPIONSaveExclude(context = "*")
@YAPIONLoadExclude(context = "*")
@SerializerImplementation
public class ExceptionSerializer implements InternalSerializer<Exception> {

    @Override
    public String type() {
        return "java.lang.Exception";
    }

    @Override
    public YAPIONAnyType serialize(SerializeData<Exception> serializeData) {
        YAPIONObject yapionObject = new YAPIONObject();
        yapionObject.add(TYPE_IDENTIFIER, type());
        yapionObject.add(EXCEPTION_IDENTIFIER, serializeData.object.getClass().getTypeName());
        yapionObject.add("message", serializeData.object.getMessage());
        yapionObject.add("cause", serializeData.serialize(serializeData.object.getCause()));
        yapionObject.add("stacktrace", serializeData.serialize(serializeData.object.getStackTrace()));
        return yapionObject;
    }

    @Override
    public Exception deserialize(DeserializeData<? extends YAPIONAnyType> deserializeData) {
        YAPIONObject yapionObject = (YAPIONObject) deserializeData.object;
        Exception exception = (Exception) ReflectionsUtils.constructObject(yapionObject.getValue(EXCEPTION_IDENTIFIER, "").get(), false);
        deserializeData.deserialize("detailMessage", exception, yapionObject.getValue("message"));
        deserializeData.deserialize("cause", exception, yapionObject.getObject("cause"));

        YAPIONArray yapionArray = yapionObject.getArray("stacktrace");
        StackTraceElement[] stackTraceElements = new StackTraceElement[yapionArray.length()];
        for (int i = 0; i < yapionArray.length(); i++) {
            stackTraceElements[i] = (StackTraceElement) deserializeData.deserialize(yapionArray.get(i));
        }
        deserializeData.setField("stackTrace", exception, stackTraceElements);
        return exception;
    }

}
