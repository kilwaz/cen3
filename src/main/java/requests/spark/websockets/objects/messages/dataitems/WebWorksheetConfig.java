package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class WebWorksheetConfig extends JSONWeb {
    @WSDataReference(WSData.WORKSHEET_CONFIG_NAME)
    private String name = null;

    public WebWorksheetConfig() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
