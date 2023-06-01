package data.model;

import requests.spark.websockets.objects.messages.dataitems.SortFilter;
import requests.spark.websockets.objects.messages.dataitems.SortItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSortFilter {
    private List<DatabaseSort> sorts = null;

    public DatabaseSortFilter(SortFilter sortFilter) {
        sorts = new ArrayList<>();

        if (sortFilter == null) {
            return;
        }

        List<SortItem> sortItems = sortFilter.getSorts();
        if (sortItems != null) {
            for (SortItem sortItem : sortItems) {
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