package clarity.load.excel;

import clarity.Infer;
import clarity.load.data.DoubleValue;
import clarity.load.data.Record;
import clarity.load.data.Value;
import clarity.load.store.DataDictionary;
import clarity.load.store.StoredItem;
import clarity.load.store.StoredRecord;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Template {
    private static Logger log = AppLogger.logger();

    private List<Record> recordList = new ArrayList<>();

    private Record headerRecord = null;

    public Template() {

    }

    public Template headerRecord(Record headerRecord) {
        this.headerRecord = headerRecord;
        return this;
    }

    public Template addRecord(Record record) {
        recordList.add(record);
        return this;
    }

    public void verify() {

    }

    public void integrate() {
        DataDictionary dataDictionary = DataDictionary.getInstance();

        List<StoredRecord> storedRecords = new ArrayList<>();

        for (Record record : recordList) {
            StoredRecord storedRecord = dataDictionary.createStoredRecord(1);

            for (Value value : record.getValues()) {
                StoredItem storedItem = null;
                if (value instanceof DoubleValue) {
                    DoubleValue doubleValue = (DoubleValue) value;

                    if (doubleValue.getColumn() == 0) {
                        storedItem = storedRecord.getStoredItem(1);
                    } else if (doubleValue.getColumn() == 1) {
                        storedItem = storedRecord.getStoredItem(2);
                    }

                    if (storedItem != null) {
                        storedItem.value(doubleValue.getValue());
                    }
                }
            }

            storedRecords.add(storedRecord);
        }

        Infer.infer();

//        for (StoredRecord storedRecord : storedRecords) {
//            log.info("*** Record ***");
//            for (StoredItem storedItem : storedRecord.getStoredItems().values()) {
//                log.info(storedItem.getValue() + " Fresh=" + storedItem.isFresh());
//            }
//        }
    }
}
