package clarity.load.store;

import clarity.Record;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.UUID;

public class Records {
    private static Logger log = AppLogger.logger();
    private static Records instance;

    private HashMap<UUID, Record> recordHashMap = new HashMap<>();

    public void addRecord(Record record) {
        recordHashMap.put(record.getUuid(), record);
    }

    public Record findRecord() {
        return (Record) recordHashMap.values().toArray()[0];
    }

    public static Records getInstance() {
        if (instance == null) {
            instance = new Records();
        }
        return instance;
    }
}
