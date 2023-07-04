package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class SummarySectionDataItem extends JSONWeb {
    // OUTGOING
    @WSDataOutgoing
    @WSDataReference()
    private String title = null;

    public SummarySectionDataItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
