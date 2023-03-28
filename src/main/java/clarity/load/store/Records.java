package clarity.load.store;

import clarity.Entry;
import clarity.Record;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Records {
    private static Logger log = AppLogger.logger();
    private static Records instance;

    private HashMap<UUID, Record> recordHashMap = new HashMap<>();

    public void addRecord(Record record) {
        recordHashMap.put(record.getUuid(), record);
    }

    public List<Record> findRecords(String reference, Object value) {
        List<Record> records = new ArrayList<>();
        for (Record record : recordHashMap.values()) {
            Entry entry = record.get(reference);
            if (entry != null) {
                if (entry.get().getValue().toString().equals(value)) {
                    records.add(record);
                }
            }
        }

        return records;
    }

    public Record findRecord(String uuid) {
        try {
            return recordHashMap.get(UUID.fromString(uuid));
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public static Records getInstance() {
        if (instance == null) {
            instance = new Records();
        }
        return instance;
    }
}
