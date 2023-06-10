package clarity.load;

import clarity.definition.*;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Objects;

public class ConfigJSON implements Loader {
    private static Logger log = AppLogger.logger();

    private File jsonFileToLoad = null;

    public ConfigJSON() {
        super();
    }

    public ConfigJSON file(File jsonFileToLoad) {
        this.jsonFileToLoad = jsonFileToLoad;
        return this;
    }

    @Override
    public void process() {
        if (jsonFileToLoad != null && jsonFileToLoad.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(jsonFileToLoad);
                byte[] data = new byte[(int) jsonFileToLoad.length()];
                fis.read(data);
                fis.close();

                String rawData = new String(data, "UTF-8");

                JSONObject jsonObject = new JSONObject(Objects.requireNonNullElse(rawData, ""));

                JSONArray definitions = jsonObject.getJSONArray("definitions");
                for (int i = 0; i < definitions.length(); i++) {
                    JSONObject definition = definitions.getJSONObject(i);
                    Definition newDefinition = Definition.define(definition.getString("name"));
                    if (definition.has("expression")) {
                        newDefinition.expression(definition.getString("expression"));
                    }
                }

                JSONArray recordDefinitions = jsonObject.getJSONArray("record_definition");
                for (int i = 0; i < recordDefinitions.length(); i++) {
                    JSONObject recordDefinition = recordDefinitions.getJSONObject(i);

                    RecordDefinition employee = RecordDefinition.define(recordDefinition.getString("name"));
                    employee.primaryKey(Definitions.getInstance().findDefinition(recordDefinition.getString("primary_key")));

                    JSONArray recordSetDefinitions = recordDefinition.getJSONArray("definitions");
                    for (int j = 0; j < recordSetDefinitions.length(); j++) {
                        employee.addDefinition(recordSetDefinitions.getString(j));
                    }
                }

                Definitions.getInstance().rebuild();

                JSONArray worksheetConfigs = jsonObject.getJSONArray("worksheet_config");
                for (int i = 0; i < worksheetConfigs.length(); i++) {
                    JSONObject worksheetConfigJson = worksheetConfigs.getJSONObject(i);

                    WorksheetConfig worksheetConfig = WorksheetConfig.create(WorksheetConfig.class);
                    worksheetConfig.columnTitle(worksheetConfigJson.getString("column_title"));
                    worksheetConfig.columnOrder(worksheetConfigJson.getInt("column_order"));
                    worksheetConfig.definition(Definitions.getInstance().findDefinition(worksheetConfigJson.getString("definition")));
                    worksheetConfig.save();
                }

                JSONArray hierarchyTrees = jsonObject.getJSONArray("hierarchy_trees");
                for (int i = 0; i < hierarchyTrees.length(); i++) {
                    JSONObject hierarchyTreeJson = hierarchyTrees.getJSONObject(i);

                    HierarchyTree hierarchyTree = HierarchyTree.create(HierarchyTree.class);
                    hierarchyTree.name(hierarchyTreeJson.getString("name"));
                    hierarchyTree.save();
                }

                log.info("Finished loading");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("This file was processed");

    }
}