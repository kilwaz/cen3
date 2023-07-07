package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.SortFilterDataItem;
import requests.spark.websockets.objects.messages.dataitems.WorksheetStatusDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class FilteredListData extends WebSocketData {
    @WSDataIncoming
    private String definition = null;

    @WSDataIncoming
    private String nodeReference = null;

    @WSDataIncoming
    @WSDataTypeScriptClass(SortFilterDataItem.class)
    private SortFilterDataItem sortFilter = null;

    @WSDataOutgoing
    @WSDataTypeScriptClass(String.class)
    private List<String> listItem = null;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<String> getListItem() {
        return listItem;
    }

    public void setListItem(List<String> listItem) {
        this.listItem = listItem;
    }

    public String getNodeReference() {
        return nodeReference;
    }

    public void setNodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
    }

    public SortFilterDataItem getSortFilter() {
        return sortFilter;
    }

    public void setSortFilter(SortFilterDataItem sortFilter) {
        this.sortFilter = sortFilter;
    }
}
