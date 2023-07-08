package clarity.load;

import clarity.definition.*;
import clarity.load.excel.DefinedBridge;
import clarity.load.excel.DefinedTemplate;
import data.model.dao.*;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
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

                    String type = definition.getString("type");
                    Integer intType = Definition.DEFINITION_TYPE_UNDEFINED;
                    if ("Number".equalsIgnoreCase(type)) {
                        intType = Definition.DEFINITION_TYPE_NUMBER;
                    } else if ("Text".equalsIgnoreCase(type)) {
                        intType = Definition.DEFINITION_TYPE_STRING;
                    }

                    String contextType = definition.getString("context_type");
                    Integer intContextType = Definition.CONTEXT_TYPE_UNDEFINED;
                    if ("Record".equalsIgnoreCase(contextType)) {
                        intContextType = Definition.CONTEXT_TYPE_RECORD;
                    } else if ("Aggregate".equalsIgnoreCase(contextType)) {
                        intContextType = Definition.CONTEXT_TYPE_AGGREGATE;
                    } else if ("View".equalsIgnoreCase(contextType)) {
                        intContextType = Definition.CONTEXT_TYPE_VIEW;
                    }

                    Definition newDefinition = Definition.define(definition.getString("name"), intType, intContextType);

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
                    JSONObject worksheetConfigsJson = worksheetConfigs.getJSONObject(i);
                    WorksheetConfig worksheetConfig = WorksheetConfig.create(WorksheetConfig.class);
                    worksheetConfig.name(worksheetConfigsJson.getString("name"));

                    JSONArray worksheetConfigsDetails = worksheetConfigsJson.getJSONArray("worksheet_config_details");
                    for (int j = 0; j < worksheetConfigsDetails.length(); j++) {
                        JSONObject worksheetConfigJson = worksheetConfigsDetails.getJSONObject(j);

                        WorksheetConfigDetails worksheetConfigDetails = WorksheetConfigDetails.create(WorksheetConfigDetails.class);
                        worksheetConfigDetails.columnTitle(worksheetConfigJson.getString("column_title"));
                        worksheetConfigDetails.columnType(worksheetConfigJson.getString("column_type"));
                        worksheetConfigDetails.columnOrder(worksheetConfigJson.getInt("column_order"));
                        worksheetConfigDetails.worksheetConfig(worksheetConfig);
                        worksheetConfigDetails.definition(Definitions.getInstance().findDefinition(worksheetConfigJson.getString("definition")));
                        worksheetConfigDetails.save();
                    }

                    worksheetConfig.save();
                }

                JSONArray hierarchyTrees = jsonObject.getJSONArray("hierarchy_trees");
                for (int i = 0; i < hierarchyTrees.length(); i++) {
                    JSONObject hierarchyTreeJson = hierarchyTrees.getJSONObject(i);

                    HierarchyTree hierarchyTree = HierarchyTree.create(HierarchyTree.class);
                    hierarchyTree.name(hierarchyTreeJson.getString("name"));
                    hierarchyTree.save();
                }

                JSONArray importTemplates = jsonObject.getJSONArray("import_templates");
                for (int i = 0; i < importTemplates.length(); i++) {
                    JSONObject importTemplatesJson = importTemplates.getJSONObject(i);

                    DefinedTemplate definedTemplate = DefinedTemplate.create(DefinedTemplate.class);
                    definedTemplate.name(importTemplatesJson.getString("name"));
                    definedTemplate.primaryKey(Definitions.getInstance().getDefinition(importTemplatesJson.getString("primary_key")));
                    definedTemplate.save();

                    JSONArray definedBridges = importTemplatesJson.getJSONArray("defined_bridges");
                    for (int j = 0; j < definedBridges.length(); j++) {
                        JSONObject definedBridgesJson = definedBridges.getJSONObject(j);

                        DefinedBridge definedBridge = DefinedBridge.create(DefinedBridge.class);
                        definedBridge.definition(Definitions.getInstance().getDefinition(definedBridgesJson.getString("definition")));
                        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition(definedBridgesJson.getString("record_definition")));
                        definedBridge.columnTitle(definedBridgesJson.getString("column_title"));
                        definedBridge.definedTemplate(definedTemplate);
                        definedBridge.save();
                    }
                }

                JSONArray formulaContexts = jsonObject.getJSONArray("formula_context");
                for (int i = 0; i < formulaContexts.length(); i++) {
                    JSONObject formulaContextJson = formulaContexts.getJSONObject(i);

                    FormulaContext formulaContext = FormulaContext.create(FormulaContext.class);
                    formulaContext.name(formulaContextJson.getString("name"));
                    formulaContext.save();

                    JSONArray formulaContextDefinitions = formulaContextJson.getJSONArray("definitions");
                    for (int j = 0; j < formulaContextDefinitions.length(); j++) {
                        FormulaContextGroup formulaContextGroup = FormulaContextGroup.create(FormulaContextGroup.class);
                        formulaContextGroup.definition(Definitions.getInstance().getDefinition(formulaContextDefinitions.getString(j)));
                        formulaContextGroup.formulaContext(formulaContext);
                        formulaContextGroup.save();
                    }
                }

                log.info("Finished loading");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public JSONObject buildJSONFromCurrentConfiguration() {
        JSONObject root = new JSONObject();

        JSONArray definitions = new JSONArray();
        List<Definition> definitionList = Definitions.getInstance().getAllDefinitions();
        for (Definition definition : definitionList) {
            JSONObject definitionJSON = new JSONObject();
            String definitionType = null;
            if (definition.getDefinitionType() == Definition.DEFINITION_TYPE_NUMBER) {
                definitionType = "Number";
            } else if (definition.getDefinitionType() == Definition.DEFINITION_TYPE_STRING) {
                definitionType = "Text";
            }

            String contextType = null;
            if (Objects.equals(definition.getContextType(), Definition.CONTEXT_TYPE_RECORD)) {
                contextType = "Record";
            } else if (Objects.equals(definition.getContextType(), Definition.CONTEXT_TYPE_AGGREGATE)) {
                contextType = "Aggregate";
            } else if (Objects.equals(definition.getContextType(), Definition.CONTEXT_TYPE_VIEW)) {
                contextType = "View";
            }

            definitionJSON.put("type", definitionType);
            definitionJSON.put("name", definition.getName());
            definitionJSON.put("context_type", contextType);

            if (definition.isCalculated()) {
                definitionJSON.put("expression", definition.getExpression());
            }
            definitions.put(definitionJSON);
        }
        root.put("definitions", definitions);

        JSONArray recordDefinitions = new JSONArray();
        List<RecordDefinition> recordDefinitionList = Definitions.getInstance().getAllRecordDefinitions();
        for (RecordDefinition recordDefinition : recordDefinitionList) {
            JSONObject recordDefinitionJSON = new JSONObject();

            recordDefinitionJSON.put("name", recordDefinition.getName());
            recordDefinitionJSON.put("primary_key", recordDefinition.getPrimaryKey().getName());

            JSONArray definitionsInRecordArray = new JSONArray();
            List<Definition> definitionsInRecord = recordDefinition.getDefinitions();
            for (Definition definition : definitionsInRecord) {
                definitionsInRecordArray.put(definition.getName());
            }
            recordDefinitionJSON.put("definitions", definitionsInRecordArray);

            recordDefinitions.put(recordDefinitionJSON);
        }
        root.put("record_definition", recordDefinitions);


        WorksheetConfigDAO worksheetConfigDAO = new WorksheetConfigDAO();
        List<WorksheetConfig> worksheetConfigList = worksheetConfigDAO.getAllWorksheetConfigs();
        JSONArray worksheetConfigs = new JSONArray();
        for (WorksheetConfig worksheetConfig : worksheetConfigList) {
            JSONObject worksheetConfigJson = new JSONObject();
            worksheetConfigJson.put("name", worksheetConfig.getName());

            List<WorksheetConfigDetails> worksheetConfigDetailsList = new WorksheetConfigDetailsDAO().getWorksheetConfigDetails(worksheetConfig);
            JSONArray worksheetDetailsConfigs = new JSONArray();

            for (WorksheetConfigDetails worksheetConfigDetail : worksheetConfigDetailsList) {
                JSONObject worksheetConfigDetailJson = new JSONObject();

                worksheetConfigDetailJson.put("column_title", worksheetConfigDetail.getColumnTitle());
                worksheetConfigDetailJson.put("column_type", worksheetConfigDetail.getColumnType());
                worksheetConfigDetailJson.put("column_order", worksheetConfigDetail.getColumnOrder());
                worksheetConfigDetailJson.put("definition", worksheetConfigDetail.getDefinition().getName());

                worksheetDetailsConfigs.put(worksheetConfigDetailJson);
            }

            worksheetConfigJson.put("worksheet_config_details", worksheetDetailsConfigs);
            worksheetConfigs.put(worksheetConfigJson);
        }
        root.put("worksheet_config", worksheetConfigs);

        JSONArray hierarchyTrees = new JSONArray();
        List<HierarchyTree> hierarchyTreesList = new HierarchyTreeDAO().getAllTrees();
        for (HierarchyTree hierarchyTree : hierarchyTreesList) {
            JSONObject hierarchyTreeJSON = new JSONObject();

            hierarchyTreeJSON.put("name", hierarchyTree.getName());

            hierarchyTrees.put(hierarchyTreeJSON);
        }
        root.put("hierarchy_trees", hierarchyTrees);

        JSONArray definedTemplates = new JSONArray();
        List<DefinedTemplate> definedTemplateList = new DefinedTemplateDAO().getAllDefinedTemplates();
        for (DefinedTemplate definedTemplate : definedTemplateList) {
            JSONObject definedTemplateJSON = new JSONObject();

            definedTemplateJSON.put("name", definedTemplate.getName());
            definedTemplateJSON.put("primary_key", definedTemplate.getPrimaryKey().getName());

            JSONArray definedBridges = new JSONArray();
            List<DefinedBridge> definedBridgeList = definedTemplate.getDefinedBridges();
            for (DefinedBridge definedBridge : definedBridgeList) {
                JSONObject definedBridgeJSON = new JSONObject();

                definedBridgeJSON.put("definition", definedBridge.getDefinition().getName());
                definedBridgeJSON.put("record_definition", definedBridge.getRecordDefinition().getName());
                definedBridgeJSON.put("column_title", definedBridge.getColumnTitle());

                definedBridges.put(definedBridgeJSON);
            }
            definedTemplateJSON.put("defined_bridges", definedBridges);
            definedTemplates.put(definedTemplateJSON);
        }
        root.put("import_templates", definedTemplates);

        JSONArray formulaContexts = new JSONArray();
        FormulaContextDAO formulaContextDAO = new FormulaContextDAO();
        List<FormulaContext> formulaContextList = formulaContextDAO.getAllFormulaContexts();
        for (FormulaContext formulaContext : formulaContextList) {
            JSONObject formulaContextJSON = new JSONObject();
            formulaContextJSON.put("name", formulaContext.getName());

            JSONArray definitionsJson = new JSONArray();
            List<FormulaContextGroup> formulaContextGroups = new FormulaContextGroupDAO().getFormulaContextGroupByFormulaContext(formulaContext);
            for (FormulaContextGroup formulaContextGroup : formulaContextGroups) {
                definitionsJson.put(formulaContextGroup.getDefinition().getName());
            }
            formulaContextJSON.put("definitions", definitionsJson);
            formulaContexts.put(formulaContextJSON);
        }
        root.put("formula_context", formulaContexts);

        return root;
    }
}