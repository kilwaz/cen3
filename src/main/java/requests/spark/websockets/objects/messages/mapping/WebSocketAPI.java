package requests.spark.websockets.objects.messages.mapping;

import error.Error;

import java.lang.reflect.Method;
import java.util.HashMap;

public class WebSocketAPI {
    private HashMap<String, DataItemModel> dataItemModels = new HashMap<>();
    private Class linkClass;

    WebSocketAPI() {
        WebSocketLinkClass webSocketLinkClass = getClass().getAnnotation(WebSocketLinkClass.class);
        this.linkClass = webSocketLinkClass.linkClass();
    }

    public void link(String variableName, Method objectSetMethod, Method objectGetMethod) {
        dataItemModels.put(variableName, new DataItemModel(variableName, objectSetMethod, objectGetMethod));
    }

    public Method method(String methodName, Class<?>... parameterTypes) {
        try {
            return getLinkClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException ex) {
            Error.DATA_LINK_METHOD_NOT_FOUND.record()
                    .additionalInformation("Class: " + getLinkClass().getName())
                    .additionalInformation("MethodName: " + methodName)
                    .create(ex);
        }

        return null;
    }

    public DataItemModel getDataItemModel(String variableName) {
        return dataItemModels.get(variableName);
    }

    public HashMap<String, DataItemModel> getDataItemModels() {
        return dataItemModels;
    }

    private Class getLinkClass() {
        return linkClass;
    }
}
