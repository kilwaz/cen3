package requests.spark.websockets.objects.messages.mapping;

import core.builders.requests.WebSocketMessageMapping;
import data.*;
import data.model.ModelColumn;
import data.model.dao.DAO;
import data.model.objects.json.JSONContainer;
import error.Error;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketAction<WSMessage extends Message, WSAPI extends WebSocketAPI> {
    public void incoming(JSONContainer jsonContainerDecoder) {
        try {
            JSONObject jsonObjectDecoded = jsonContainerDecoder.toJSONObject();

            Class mappingClass = WebSocketMessageMapping.mappingClass(jsonObjectDecoded.getString("_type"));
            Class dataClass = WebSocketMessageMapping.dataClass(mappingClass);

            WSMessage wsMessage = (WSMessage) mappingClass.getConstructor().newInstance();
            WSAPI wsData = (WSAPI) dataClass.getConstructor().newInstance();

            for (String key : jsonObjectDecoded.keySet()) {
                wsData.getDataItemModel(key).getObjectSetMethod().invoke(jsonObjectDecoded.get(key));
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException ex) {
            ex.printStackTrace();
        }
    }

    public void outgoing(WSMessage wsMessage) {

        // Building the select query string
        var selectQueryBuilder = new StringBuilder();
        selectQueryBuilder.append("select ");
        var firstColumn = true;
        for (ModelColumn modelColumn : dbLink.getModelColumns()) {
            if (firstColumn) {
                selectQueryBuilder
                        .append(modelColumn.getColumnName())
                        .append(" ");
                firstColumn = false;
            } else {
                selectQueryBuilder.append(", ")
                        .append(modelColumn.getColumnName())
                        .append(" ");
            }
        }
        selectQueryBuilder
                .append("from ")
                .append(dbLink.getTableName())
                .append(" where uuid = ?");

        var selectResult = (SelectResult) new SelectQuery(selectQueryBuilder.toString())
                .addParameter(dbObject.getUuidString())
                .execute();

        for (var resultRow : selectResult.getResults()) {
            for (var modelColumn : dbLink.getModelColumns()) {
                try {
                    if (modelColumn.getObjectLoadMethod() != null) {
                        var loadMethodParameter = modelColumn.getObjectLoadMethod().getParameterTypes();
                        Class loadParameterClass = null;

                        if (loadMethodParameter.length > 0) {
                            loadParameterClass = loadMethodParameter[0];
                        }
                        if (loadParameterClass != null) {
                            if (loadParameterClass.equals(String.class)) {  // STRING
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getString(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(Double.class)) { // DOUBLE
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getDouble(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(DateTime.class)) { // DATE TIME
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getDateTime(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(Integer.class)) { // INTEGER
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getInt(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(Boolean.class)) { // BOOLEAN
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getBoolean(modelColumn.getColumnName()));
                            } else if (loadParameterClass.equals(JSONObject.class)) { // JSON OBJECT
                                var jsonStr = resultRow.getString(modelColumn.getColumnName());
                                if (jsonStr != null) {
                                    var jsonContainer = new JSONContainer(jsonStr);
                                    modelColumn.getObjectLoadMethod().invoke(dbObject, jsonContainer.toJSONObject());
                                }
                            } else if (loadParameterClass.equals(UUID.class)) { // UUID
                                String uuid = resultRow.getString(modelColumn.getColumnName());
                                if (uuid != null && !uuid.isEmpty()) {
                                    modelColumn.getObjectLoadMethod().invoke(dbObject, DAO.UUIDFromString(uuid));
                                }
                            } else if (loadParameterClass.equals(Object.class)) { // OBJECT
                                modelColumn.getObjectLoadMethod().invoke(dbObject, resultRow.getColumnObject(modelColumn.getColumnName()));
                            }
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Error.DATABASE_OBJECT_METHOD_NOT_FOUND.record().create(ex);
                } catch (IllegalArgumentException ex) {
                    Error.DATABASE_OBJECT_METHOD_NOT_FOUND.record().additionalInformation("Method name: " + modelColumn.getObjectLoadMethod().getName()).create(ex);
                }
            }
        }
    }
}
