package requests.spark.websockets.objects.messages.dataitems;

import clarity.definition.FormulaContextGroup;
import data.model.dao.FormulaContextGroupDAO;
import org.json.JSONArray;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

import java.util.List;

public class FormulaContextDataItem extends JSONWeb {
    @WSDataReference()
    private String uuid = null;

    @WSDataReference()
    private String name = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(String.class)
    @WSDataReference()
    private JSONArray definitionIds = null;

    public FormulaContextDataItem(clarity.definition.FormulaContext formulaContext) {
        this.uuid = formulaContext.getUuidString();
        this.name = formulaContext.getName();

        List<FormulaContextGroup> formulaContextGroups = new FormulaContextGroupDAO().getFormulaContextGroupByFormulaContext(formulaContext);
        this.definitionIds = new JSONArray();
        for (FormulaContextGroup formulaContextGroup : formulaContextGroups) {
            this.definitionIds.put(formulaContextGroup.getDefinition().getUuidString());
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getDefinitionIds() {
        return definitionIds;
    }

    public void setDefinitionIds(JSONArray definitionIds) {
        this.definitionIds = definitionIds;
    }
}
