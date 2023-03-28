package clarity.load.excel;

import clarity.Infer;
import clarity.load.data.Record;
import clarity.load.data.StringValue;
import clarity.load.data.Value;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

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
        for (Value headerValue : headerRecord.getValues()) {
            if (headerValue instanceof StringValue) {
                log.info(((StringValue) headerValue).getValue());
            }
        }

        // Using the headers somehow match this import to an object..

        // If an ID matches update the values
        // If an ID is new then insert the values

        //Infer.infer();
    }
}
