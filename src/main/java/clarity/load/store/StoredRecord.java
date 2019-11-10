package clarity.load.store;

import java.util.ArrayList;
import java.util.List;

public class StoredRecord {
    private List<StoredItem> storedItems = new ArrayList<>();

    private Boolean fresh = false;

    public StoredRecord() {

    }

    public StoredRecord addStoredItem(StoredItem storedItem) {
        storedItems.add(storedItem);
        fresh = false;
        return this;
    }

    public List<StoredItem> getStoredItems() {
        return storedItems;
    }

    public Boolean isFresh() {
        return fresh;
    }
}
