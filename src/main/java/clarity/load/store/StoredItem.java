package clarity.load.store;

public class StoredItem {
    private DataItem dataItem;

    private Double doubleValue = null;
    private Boolean fresh = false;

    public StoredItem(DataItem dataItem) {
        this.dataItem = dataItem;
    }

    public StoredItem value(Double doubleValue) {
        this.doubleValue = doubleValue;
        fresh = DataItem.TYPE_STATIC.equals(dataItem.getType());
        return this;
    }

    public Double getValue() {
        return this.doubleValue;
    }

    public Boolean isFresh() {
        return fresh;
    }
}
