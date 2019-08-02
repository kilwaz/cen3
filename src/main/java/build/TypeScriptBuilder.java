package build;

import error.Error;
import log.AppLogger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import requests.spark.websockets.objects.messages.dataobjects.AdminGameData;
import requests.spark.websockets.objects.messages.dataobjects.WebSocketData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class TypeScriptBuilder {
    private static Logger log = AppLogger.logger();

    private static String BUILD_DIRECTORY = "C:\\Users\\alex\\IdeaProjects\\groupGames\\src\\main\\ngapp\\src\\app\\wsObjects";
    private static String nl = System.lineSeparator();
    private static String tab = "\t";

    public static void build() {
        Set<Class<? extends WebSocketData>> dataObjects = new Reflections("requests.spark.websockets.objects.messages.dataobjects").getSubTypesOf(WebSocketData.class);
        for (Class clazz : dataObjects) {
            if (clazz.equals(AdminGameData.class)) {
                try {
                    Field[] fields = clazz.getDeclaredFields();

                    // Class name with 'Data' removed
                    String tsClassName = clazz.getSimpleName().substring(0, clazz.getSimpleName().length() - 4);
                    // Class name with lower case first letter for file name to use
                    String tsFileName = Character.toLowerCase(tsClassName.charAt(0)) + tsClassName.substring(1);

                    StringBuilder tsClassBuilder = new StringBuilder();
                    tsClassBuilder.append("import {Message} from \"./message\";").append(nl);

                    for (Field field : fields) {
                        if (field.isAnnotationPresent(WSDataJSONArrayClass.class)) {
                            WSDataJSONArrayClass wsDataJSONArrayClass = field.getAnnotation(WSDataJSONArrayClass.class);
                            tsClassBuilder.append("import {").append(wsDataJSONArrayClass.value().getSimpleName()).append("} from \"../")
                                    .append(StringUtils.lowerCase(wsDataJSONArrayClass.value().getSimpleName())).append("\";").append(nl);
                        }
                    }

                    tsClassBuilder.append(nl);
                    tsClassBuilder.append("export class ").append(tsClassName).append(" extends Message {").append(nl);

                    for (Field field : fields) {
                        tsClassBuilder.append(tab).append("private _").append(field.getName()).append(":");
                        tsClassBuilder.append(" ").append(classResolve(field, true)).append(";");
                        tsClassBuilder.append(nl);
                    }

                    tsClassBuilder.append(tab).append(nl);

                    tsClassBuilder.append(tab).append("constructor() {").append(nl);
                    tsClassBuilder.append(tab).append(tab).append("super();").append(nl);
                    tsClassBuilder.append(tab).append(tab).append("this.type = \"").append(tsClassName).append("\";").append(nl);
                    tsClassBuilder.append(tab).append("}").append(nl);

                    tsClassBuilder.append(nl);

                    tsClassBuilder.append(tab).append("decodeResponse(msgRaw: any) {").append(nl);

                    for (Field field : fields) {
                        if (field.isAnnotationPresent(WSDataOutgoing.class)) {
                            tsClassBuilder.append(tab).append(tab).append("this._").append(field.getName()).append(" = msgRaw.").append(field.getName()).append(";").append(nl);
                        }
                    }

                    tsClassBuilder.append(tab).append("}").append(nl);
                    tsClassBuilder.append(nl);

                    // Getters
                    for (Field field : fields) {
                        tsClassBuilder.append(tab).append("get ").append(field.getName()).append("(): ").append(classResolve(field, false)).append(" {").append(nl);
                        tsClassBuilder.append(tab).append(tab).append("return this._").append(field.getName()).append(";").append(nl);
                        tsClassBuilder.append(tab).append("}").append(nl);
                        tsClassBuilder.append(tab).append(nl);
                    }

                    // Setters
                    for (Field field : fields) {
                        tsClassBuilder.append(tab).append("set ").append(field.getName()).append("(value: ").append(classResolve(field, false)).append(") {").append(nl);
                        tsClassBuilder.append(tab).append(tab).append("this._").append(field.getName()).append(" = value;").append(nl);
                        tsClassBuilder.append(tab).append("}").append(nl);
                        tsClassBuilder.append(tab).append(nl);
                    }

                    tsClassBuilder.append("}");

                    FileUtils.deleteQuietly(new File(BUILD_DIRECTORY + "\\" + tsFileName + ".ts"));
                    FileUtils.writeStringToFile(new File(BUILD_DIRECTORY + "\\" + tsFileName + ".ts"), tsClassBuilder.toString(), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    Error.TYPE_SCRIPT_GENERATION.record().create(ex);
                }
            }
        }
    }

    private static String classResolve(Field field, Boolean declaration) {
        switch (field.getType().getSimpleName()) {
            case "String":
            case "UUID":
                return "string";
            case "JSONArray":
                if (field.isAnnotationPresent(WSDataJSONArrayClass.class)) {
                    WSDataJSONArrayClass wsDataJSONArrayClass = field.getAnnotation(WSDataJSONArrayClass.class);

                    if (declaration) {
                        return "Array<" + wsDataJSONArrayClass.value().getSimpleName() + "> = []";
                    } else {
                        return "Array<" + wsDataJSONArrayClass.value().getSimpleName() + ">";
                    }
                }
        }

        return "";
    }
}
