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

import java.util.List;

public class Template {
    private static Logger log = AppLogger.logger();

    private LoadedRecord headerRecord = null;

    public Template() {
        DefinedTemplate definedTemplate = DefinedTemplate.create(DefinedTemplate.class);
        definedTemplate.name("Test import");
        definedTemplate.primaryKey(Definitions.getInstance().getDefinition("ID"));
        definedTemplate.save();

        DefinedBridge definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("A"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Test A");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("B"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Test B");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("ID"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("ID");
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
        Definition templatePrimaryKey = definedTemplateLoaded.getPrimaryKey();

        Value primaryValue = loadedRecord.getValueByColumnNumber(0);
        DatabaseCollect databaseCollect = DatabaseCollect
                .create()
                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
                .state(RecordState.RAW);

        if (primaryValue instanceof StringValue) {
            log.info("String primary");
            databaseCollect.primaryKey(((StringValue) primaryValue).getValue());
        } else if (primaryValue instanceof DoubleValue) {
            log.info("Double primary");
            databaseCollect.primaryKey("" + ((DoubleValue) primaryValue).getValue().intValue());
//            ((DoubleValue) primaryValue).getValue();
        }
        Record dbRecord = databaseCollect.singleResult();

        log.info("Primary is " + primaryValue.getValue());

        for (Value recordValue : loadedRecord.getValues()) {
            if (!recordValue.equals(primaryValue)) { // Don't update the primary key just to be safe, this should match anyway
                DefinedBridge definedBridge = definedTemplateLoaded.getDefinedBridgeByName(recordValue.getColumnName());

                Entry entry = dbRecord.get(definedBridge.getDefinition());
                EntryValue entryValue = entry.get();
                entryValue.setValue(recordValue.getValue());

                log.info(recordValue.getColumnName() + " -> " + recordValue.getValue());
            }
        }

        dbRecord.save(RecordState.RAW);
        Infer.me(dbRecord);

        log.info("Saving..");
    }
}
