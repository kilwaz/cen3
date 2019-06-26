package requests.spark.websockets.objects.messages.mapping;

import java.lang.reflect.Method;

public class DataItemModel {
    private String variableName;
    private Method objectSetMethod;
    private Method objectGetMethod;

    public DataItemModel(String variableName, Method objectSetMethod, Method objectGetMethod) {
        this.variableName = variableName;
        this.objectSetMethod = objectSetMethod;
        this.objectGetMethod = objectGetMethod;
    }

    public String getVariableName() {
        return variableName;
    }

    public Method getObjectSetMethod() {
        return objectSetMethod;
    }

    public Method getObjectGetMethod() {
        return objectGetMethod;
    }
}
