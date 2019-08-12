package build;

import error.Error;
import log.AppLogger;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.dataobjects.WebSocketData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class TypeScriptBuilder {
    private static Logger log = AppLogger.logger();

    private static String nl = System.lineSeparator();
    private static String tab = "\t";

    public static void build() {
        // Generate Actions
        String pathToWsActions = generatePath("wsActions");
        String autoGenerateMessage = "THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE";

        Set<Class<? extends WebSocketData>> wsActions = new Reflections("requests.spark.websockets.objects.messages.dataobjects").getSubTypesOf(WebSocketData.class);
        for (Class clazz : wsActions) {
            try {
                Field[] fields = clazz.getDeclaredFields();

                // Class name with 'Data' removed
                String tsClassName = clazz.getSimpleName().substring(0, clazz.getSimpleName().length() - 4);
                // Class name with lower case first letter for file name to use
                String tsFileName = Character.toLowerCase(tsClassName.charAt(0)) + tsClassName.substring(1);

                StringBuilder tsClassBuilder = new StringBuilder();
                tsClassBuilder.append("//").append(autoGenerateMessage).append(nl);
                tsClassBuilder.append("import {Message} from \"./message\";").append(nl);

                for (Field field : fields) {
                    if (field.isAnnotationPresent(WSDataJSONArrayClass.class)) {
                        WSDataJSONArrayClass wsDataJSONArrayClass = field.getAnnotation(WSDataJSONArrayClass.class);
                        if (!tsClassBuilder.toString().contains("import {" + wsDataJSONArrayClass.value().getSimpleName() + "}")) {
                            tsClassBuilder.append("import {").append(wsDataJSONArrayClass.value().getSimpleName()).append("} from \"../wsObjects/")
                                    .append(Character.toLowerCase(wsDataJSONArrayClass.value().getSimpleName().charAt(0)))
                                    .append(wsDataJSONArrayClass.value().getSimpleName().substring(1))
                                    .append("\";").append(nl);
                        }
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

                FileUtils.deleteQuietly(new File(pathToWsActions + "\\" + tsFileName + ".ts"));
                FileUtils.writeStringToFile(new File(pathToWsActions + "\\" + tsFileName + ".ts"), tsClassBuilder.toString(), StandardCharsets.UTF_8);
            } catch (IOException ex) {
                Error.TYPE_SCRIPT_GENERATION.record().create(ex);
            }
        }

        // Generate Actors
        String pathToWsObjects = generatePath("wsObjects");
        Set<Class<? extends JSONWeb>> actorClasses = new Reflections("game.actors").getSubTypesOf(JSONWeb.class);
        for (Class clazz : actorClasses) {
            try {
                Field[] fields = clazz.getDeclaredFields();

                // Class name with 'Data' removed
                String tsClassName = clazz.getSimpleName().substring(0, clazz.getSimpleName().length());
                // Class name with lower case first letter for file name to use
                String tsFileName = Character.toLowerCase(tsClassName.charAt(0)) + tsClassName.substring(1);

                StringBuilder tsClassBuilder = new StringBuilder();
                tsClassBuilder.append("//").append(autoGenerateMessage).append(nl);

                for (Field field : fields) {
                    if (field.isAnnotationPresent(WSDataTypeScriptClass.class)) {
                        WSDataTypeScriptClass wsDataTypeScriptClass = field.getAnnotation(WSDataTypeScriptClass.class);
                        if (!tsClassBuilder.toString().contains("import {" + wsDataTypeScriptClass.value().getSimpleName() + "}")) {
                            tsClassBuilder.append("import {").append(wsDataTypeScriptClass.value().getSimpleName()).append("} from \"./")
                                    .append(Character.toLowerCase(wsDataTypeScriptClass.value().getSimpleName().charAt(0)))
                                    .append(wsDataTypeScriptClass.value().getSimpleName().substring(1))
                                    .append("\";").append(nl);
                        }
                    }
                }

                tsClassBuilder.append(nl);
                tsClassBuilder.append("export class ").append(tsClassName).append(" {").append(nl);

                for (Field field : fields) {
                    tsClassBuilder.append(tab).append("private _").append(field.getName()).append(":");
                    tsClassBuilder.append(" ").append(classResolve(field, true)).append(";");
                    tsClassBuilder.append(nl);
                }

                tsClassBuilder.append(tab).append(nl);

                tsClassBuilder.append(tab).append("constructor() {").append(nl);
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

                FileUtils.deleteQuietly(new File(pathToWsObjects + "\\" + tsFileName + ".ts"));
                FileUtils.writeStringToFile(new File(pathToWsObjects + "\\" + tsFileName + ".ts"), tsClassBuilder.toString(), StandardCharsets.UTF_8);
            } catch (IOException ex) {
                Error.TYPE_SCRIPT_GENERATION.record().create(ex);
            }
        }
    }

    private static String generatePath(String folderName) {
        String path = "";
        try {
            path = new File(TypeScriptBuilder.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            path = path.replace("target\\classes", "");
            path += "src\\main\\ngapp\\src\\app\\" + folderName;
        } catch (URISyntaxException ex) {
            Error.TYPE_SCRIPT_BUILD_FOLDER_URI.record().create(ex);
        }

        return path;
    }

    private static String classResolve(Field field, Boolean declaration) {
        switch (field.getType().getSimpleName()) {
            case "String":
            case "UUID":
                return "string";
            case "Integer":
                return "number";
            case "Boolean":
                return "boolean";
            case "JSONArray":
                if (field.isAnnotationPresent(WSDataJSONArrayClass.class)) {
                    WSDataJSONArrayClass wsDataJSONArrayClass = field.getAnnotation(WSDataJSONArrayClass.class);

                    if (declaration) {
                        return "Array<" + wsDataJSONArrayClass.value().getSimpleName() + "> = []";
                    } else {
                        return "Array<" + wsDataJSONArrayClass.value().getSimpleName() + ">";
                    }
                }
            case "List":
                if (field.isAnnotationPresent(WSDataTypeScriptClass.class)) {
                    WSDataTypeScriptClass wsDataTypeScriptClass = field.getAnnotation(WSDataTypeScriptClass.class);

                    if (declaration) {
                        return "Array<" + wsDataTypeScriptClass.value().getSimpleName() + "> = []";
                    } else {
                        return "Array<" + wsDataTypeScriptClass.value().getSimpleName() + ">";
                    }
                }
            default:
                if (field.isAnnotationPresent(WSDataTypeScriptClass.class)) {
                    WSDataTypeScriptClass wsDataTypeScriptClass = field.getAnnotation(WSDataTypeScriptClass.class);

                    return wsDataTypeScriptClass.value().getSimpleName();
                } else {
                    return "{{UNMATCHED CLASS}}";
                }
        }
    }
}
