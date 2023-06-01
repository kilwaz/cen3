package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class SortItem extends JSONWeb {
    @WSDataReference(WSData.SORT_FILTER_ITEM_DEFINITION)
    private String definitionName = null;

    @WSDataReference(WSData.SORT_FILTER_ITEM_DIRECTION)
    private String direction = null;

    public SortItem() {

    }

    public String getDefinitionName() {
        return definitionName;
    }

    public void setDefinitionName(String definitionName) {
        this.definitionName = definitionName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}