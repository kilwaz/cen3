package requests.spark.websockets.objects.messages.mapping;

import error.Error;

import java.lang.reflect.Method;
import java.util.HashMap;

public class WebSocketAPI {
    private HashMap<String, DataItemModel> dataItemInModels = new HashMap<>();
    private HashMap<String, DataItemModel> dataItemOutModels = new HashMap<>();
    private Class linkClass;

    WebSocketAPI() {
        WebSocketLinkClass webSocketLinkClass = getClass().getAnnotation(WebSocketLinkClass.class);
        this.linkClass = webSocketLinkClass.linkClass();
    }

    public void inLink(String variableName, Method objectSetMethod, Method objectGetMethod) {
        dataItemInModels.put(variableName, new DataItemModel(variableName, objectSetMethod, objectGetMethod));
    }

    public void outLink(String variableName, Method objectSetMethod, Method objectGetMethod) {
        dataItemOutModels.put(variableName, new DataItemModel(variableName, objectSetMethod, objectGetMethod));
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

    public DataItemModel getDataItemInModel(String variableName) {
        return dataItemInModels.get(variableName);
    }

    public HashMap<String, DataItemModel> getDataItemInModels() {
        return dataItemInModels;
    }

    public DataItemModel getDataItemOutModel(String variableName) {
        return dataItemOutModels.get(variableName);
    }

    public HashMap<String, DataItemModel> getDataItemOutModels() {
        return dataItemOutModels;
    }

    private Class getLinkClass() {
        return linkClass;
    }
}
