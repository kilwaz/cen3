package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class DefinitionDataItem extends JSONWeb {
    @WSDataReference()
    private String uuid = null;

    @WSDataReference()
    private String expression = null;

    @WSDataReference()
    private String name = null;

    public DefinitionDataItem() {

    }

    public DefinitionDataItem(clarity.definition.Definition definitionClarity) {
        this.uuid = definitionClarity.getUuidString();
        this.expression = definitionClarity.getExpression();
        this.name = definitionClarity.getName();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
