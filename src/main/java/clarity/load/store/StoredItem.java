package clarity.load.store;

import clarity.Infer;

import java.util.UUID;

public class StoredItem {
    private DataItem dataItem;
    private StoredRecord storedRecord;

    private UUID uuid = UUID.randomUUID();

    private Double doubleValue = null;
    private Boolean fresh = false;

    public StoredItem(StoredRecord storedRecord, DataItem dataItem) {
        this.dataItem = dataItem;
        this.storedRecord = storedRecord;
        if (DataItem.TYPE_CALCULATED.equals(dataItem.getType())) {
//            Infer.me(this);
        }
    }

    public StoredItem value(Double doubleValue) {
        this.doubleValue = doubleValue;
        fresh = DataItem.TYPE_STATIC.equals(dataItem.getType());
        if (!fresh) {
//            Infer.me(this);
        }
        return this;
    }

    public Double getValue() {
        return this.doubleValue;
    }

    public Boolean isFresh() {
        return fresh;
    }

    public UUID getUuid() {
        return uuid;
    }

    public DataItem getDataItem() {
        return dataItem;
    }

    public StoredItem fresh(Boolean fresh) {
        this.fresh = fresh;
        return this;
    }

    public StoredRecord getStoredRecord() {
        return storedRecord;
    }
}
