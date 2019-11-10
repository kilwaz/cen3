package clarity.load.store;

import java.util.HashMap;

public class DataDictionary {
    private static DataDictionary instance = null;

    private HashMap<Integer, DataItem> dataItems = new HashMap<>();


    private DataDictionary() {
        dataItems.put(1, new DataItem(1, DataItem.TYPE_STATIC));
        dataItems.put(2, new DataItem(2, DataItem.TYPE_STATIC));
        dataItems.put(3, new DataItem(3, DataItem.TYPE_CALCULATED));
    }

    public static DataDictionary getInstance() {
        if (instance == null) {
            instance = new DataDictionary();
        }
        return instance;
    }

    public StoredItem createStoredItem(int id) {
        if (dataItems.containsKey(id)) {
            return new StoredItem(dataItems.get(id));
        }
        return null;
    }

    public StoredRecord createStoredRecord() {
        return new StoredRecord();
    }
}
