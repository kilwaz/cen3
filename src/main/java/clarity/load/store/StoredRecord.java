package clarity.load.store;

import java.util.HashMap;
import java.util.UUID;

public class StoredRecord {
    private DataRecord dataRecord;
    private UUID uuid = UUID.randomUUID();
    private HashMap<UUID, StoredItem> storedItems = new HashMap<>();
    private HashMap<Integer, StoredItem> storedItemsId = new HashMap<>();

    private Boolean fresh = false;

    public StoredRecord(DataRecord dataRecord) {
        this.dataRecord = dataRecord;

        for (DataItem dataItem : dataRecord.getDataItems()) {
            StoredItem storedItem = new StoredItem(this, dataItem);
            storedItems.put(storedItem.getUuid(), storedItem);
            storedItemsId.put(storedItem.getDataItem().getId(), storedItem);
        }
    }

    public HashMap<UUID, StoredItem> getStoredItems() {
        return storedItems;
    }

    public StoredItem getStoredItem(int id) {
        return storedItemsId.get(id);
    }

    public StoredItem getStoredItem(UUID uuid) {
        return storedItems.get(uuid);
    }

    public Boolean isFresh() {
        return fresh;
    }

    public UUID getUuid() {
        return uuid;
    }
}
