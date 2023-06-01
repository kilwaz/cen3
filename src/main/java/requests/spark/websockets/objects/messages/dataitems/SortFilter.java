package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class SortFilter extends JSONWeb {

    @WSDataReference(WSData.SORT_FILTER_SORTS)
    @WSDataJSONArrayClass(SortItem.class)
    @WSDataTypeScriptClass(SortItem.class)
    private List<SortItem> sorts = null;

    public List<SortItem> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortItem> sorts) {
        this.sorts = sorts;
    }
}
