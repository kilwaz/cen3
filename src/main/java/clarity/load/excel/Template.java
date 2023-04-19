package clarity.load.excel;

import clarity.Entry;
import clarity.EntryValue;
import clarity.Infer;
import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordState;
import clarity.load.data.DoubleValue;
import clarity.load.data.LoadedRecord;
import clarity.load.data.StringValue;
import clarity.load.data.Value;
import data.model.DatabaseCollect;
import data.model.dao.DefinedTemplateDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

public class Template {
    private static Logger log = AppLogger.logger();

    private LoadedRecord headerRecord = null;

    public Template() {
        DefinedTemplate definedTemplate = DefinedTemplate.create(DefinedTemplate.class);
        definedTemplate.name("Test import");
        definedTemplate.primaryKey(Definitions.getInstance().getDefinition("Employee_Number"));
        definedTemplate.save();

        DefinedBridge definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("First_Name"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("First Name");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Last_Name"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Last Name");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Preferred_Name"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Preferred Name");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Employee_Number"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Employee Number");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();
    }

    public Template headerRecord(LoadedRecord headerRecord) {
        this.headerRecord = headerRecord;
        return this;
    }

    public void verify() {

    }

    public void integrate(LoadedRecord loadedRecord) {
        DefinedTemplateDAO definedTemplateDAO = new DefinedTemplateDAO();
        DefinedTemplate definedTemplateLoaded = definedTemplateDAO.getDefinedTemplateByName("Test import");
//        Definition templatePrimaryKey = definedTemplateLoaded.getPrimaryKey();

        Value primaryValue = loadedRecord.getValueByColumnNumber(0);
        DatabaseCollect databaseCollect = DatabaseCollect
                .create()
                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
                .state(RecordState.RAW);

        if (primaryValue instanceof StringValue) {
            databaseCollect.primaryKey(((StringValue) primaryValue).getValue());
        } else if (primaryValue instanceof DoubleValue) {
            databaseCollect.primaryKey("" + ((DoubleValue) primaryValue).getValue().intValue());
        }
        Record dbRecord = databaseCollect.singleResult();

//        log.info("Primary is " + primaryValue.getValue());

        Boolean newRecord = false;
        if (dbRecord == null) {
            dbRecord = Record.create("Employee");
            newRecord = true;
        }

        for (Value recordValue : loadedRecord.getValues()) {
            if (!recordValue.equals(primaryValue) || newRecord) { // Don't update the primary key just to be safe, this should match anyway
                DefinedBridge definedBridge = definedTemplateLoaded.getDefinedBridgeByName(recordValue.getColumnName());

                Entry entry = dbRecord.get(definedBridge.getDefinition());
                if (entry == null) {
                    entry = new Entry(dbRecord, definedBridge.getDefinition());
                    dbRecord.set(entry);
                }
                EntryValue entryValue = entry.get();
                entryValue.setValue(recordValue.getValue());

//                log.info(recordValue.getColumnName() + " -> " + recordValue.getValue());
            }
        }

        dbRecord.save(RecordState.RAW);
        Infer.me(dbRecord);

//        log.info("Saving..");
    }
}
