package data.model.objects.json;

import data.model.DatabaseObject;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class JSONMapper<DBO extends DatabaseObject> {
    private static Logger log = AppLogger.logger();

    public JSONObject process(DBO databaseObject, List<String> filter) {
        JSONObject jsonObject = new JSONObject();
        if (databaseObject != null) {
            Class mappingClass = databaseObject.getClass();

            Method[] methods = mappingClass.getMethods();

            for (Method method : methods) {
                JSONMappable jsonMappable = method.getAnnotation(JSONMappable.class);
                if (jsonMappable != null) { // Mappable annotation exists
                    try {
                        if (filter.size() == 0 || filter.contains(jsonMappable.value())) {
                            // If there is a child class that is another database object, run the same process and then store the JSONObject returned
                            if (method.getReturnType().getSuperclass().equals(DatabaseObject.class)) {
                                JSONObject childDBOJSON = process((DBO) method.invoke(databaseObject), filter);
                                jsonObject.put(jsonMappable.value(), childDBOJSON);
                            } else {
                                jsonObject.put(jsonMappable.value(), method.invoke(databaseObject));
                            }
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return jsonObject;
    }

    public JSONArray process(List<DBO> databaseObjectList, List<String> filter) {
        JSONArray objects = new JSONArray();

        for (DBO databaseObject : databaseObjectList) {
            objects.put(process(databaseObject, filter));
        }

        return objects;
    }

    public static JSONMapper build() {
        return new JSONMapper();
    }
}
