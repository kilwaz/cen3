package data.model;

import requests.spark.websockets.objects.messages.dataitems.SortFilterDataItem;
import requests.spark.websockets.objects.messages.dataitems.SortDataItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSortFilter {
    private List<DatabaseSort> sorts = null;

    public DatabaseSortFilter(SortFilterDataItem sortFilter) {
        sorts = new ArrayList<>();

        if (sortFilter == null) {
            return;
        }

        List<SortDataItem> sortItems = sortFilter.getSorts();
        if (sortItems != null) {
            for (SortDataItem sortItem : sortItems) {
                sorts.add(new DatabaseSort(sortItem.getDefinitionName(), sortItem.getDirection()));
            }
        }
    }

    public List<DatabaseSort> getSorts() {
        return sorts;
    }

    public void setSorts(List<DatabaseSort> sorts) {
        this.sorts = sorts;
    }
}