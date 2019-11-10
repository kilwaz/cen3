package clarity.load.store;

import java.util.ArrayList;
import java.util.List;

public class DataRecord {

    private List<DataItem> dataItems = new ArrayList<>();

    private Integer id;

    public DataRecord(Integer id) {
        this.id = id;
    }

    public DataRecord addItem(DataItem dataItem) {
        dataItems.add(dataItem);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public List<DataItem> getDataItems() {
        return dataItems;
    }
}
