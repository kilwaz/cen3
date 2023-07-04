package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class SortFilterDataItem extends JSONWeb {

    @WSDataReference()
    @WSDataJSONArrayClass(SortDataItem.class)
    @WSDataTypeScriptClass(SortDataItem.class)
    private List<SortDataItem> sorts = null;

    public List<SortDataItem> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortDataItem> sorts) {
        this.sorts = sorts;
    }
}
