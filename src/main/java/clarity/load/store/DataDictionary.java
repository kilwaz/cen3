package clarity.load.store;

import java.util.HashMap;

public class DataDictionary {
    private static DataDictionary instance = null;

    private HashMap<Integer, DataItem> dataItems = new HashMap<>();
    private HashMap<Integer, DataRecord> dataRecords = new HashMap<>();


    private DataDictionary() {
        dataItems.put(1, new DataItem(1, DataItem.TYPE_STATIC));
        dataItems.put(2, new DataItem(2, DataItem.TYPE_STATIC));
        dataItems.put(3, new DataItem(3, DataItem.TYPE_CALCULATED));
        dataItems.put(4, new DataItem(4, DataItem.TYPE_CALCULATED));

        dataItems.get(3).formula("[1] + [2]");
        dataItems.get(4).formula("[1] * [2]");

        DataRecord dataRecord = new DataRecord(1);
        dataRecord.addItem(dataItems.get(1))
                .addItem(dataItems.get(2))
                .addItem(dataItems.get(3))
                .addItem(dataItems.get(4));

        dataRecords.put(dataRecord.getId(), dataRecord);
    }

    public static DataDictionary getInstance() {
        if (instance == null) {
            instance = new DataDictionary();
        }
        return instance;
    }

    public DataItem getDataItem(int id) {
        if (dataItems.containsKey(id)) {
            return dataItems.get(id);
        }
        return null;
    }

    public StoredRecord createStoredRecord(int id) {
        if (dataRecords.containsKey(id)) {
            return new StoredRecord(dataRecords.get(id));
        }
        return null;
    }
}
