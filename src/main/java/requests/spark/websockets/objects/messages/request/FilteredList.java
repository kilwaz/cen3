package requests.spark.websockets.objects.messages.request;

import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import clarity.definition.RecordState;
import data.SelectQuery;
import data.SelectResult;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.FilteredListData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.List;

@MessageType("FilteredList")
@WebSocketDataClass(FilteredListData.class)
public class FilteredList extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        FilteredListData filteredListData = (FilteredListData) this.getWebSocketData();

        Definition definition = Definitions.getInstance().getDefinition(filteredListData.getDefinition());
        RecordDefinition recordDefinition = Definitions.getInstance().getRecordDefinition("Employee");

        var selectQueryBuilder = new StringBuilder();

        selectQueryBuilder.
                append("select ").append(definition.getName()).append(" from ")
                .append(recordDefinition.getTableNameByState(RecordState.STATIC))
                .append(" group by ").append(definition.getName())
                .append(" order by ").append(definition.getName());
        var selectResult = (SelectResult) new SelectQuery(selectQueryBuilder.toString()).execute();

        List<String> itemList = new ArrayList<>();
        for (var resultRow : selectResult.getResults()) {
            String listItem = resultRow.getString(definition.getName());
            itemList.add(listItem);
        }

        filteredListData.setListItem(itemList);
    }
}
