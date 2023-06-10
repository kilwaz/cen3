package clarity.load.excel;

import clarity.Entry;
import clarity.EntryValue;
import clarity.Infer;
import clarity.Record;
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

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Assignment_Status"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Assignment Status");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Grade"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Grade");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Employment_Category"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Employment Category");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Office_Location"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Office Location");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Currency"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Currency");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Salary"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Salary");
        definedBridge.definedTemplate(definedTemplate);
        definedBridge.save();

        definedBridge = DefinedBridge.create(DefinedBridge.class);
        definedBridge.definition(Definitions.getInstance().getDefinition("Reviewing_Manager_ID"));
        definedBridge.recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"));
        definedBridge.columnTitle("Reviewing Manager ID");
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

        Boolean newRecord = false;
        if (dbRecord == null) {
            dbRecord = Record.create("Employee");
            newRecord = true;
        }

        for (Value recordValue : loadedRecord.getValues()) {
            if (!recordValue.equals(primaryValue) || newRecord) { // Don't update the primary key just to be safe, this should match anyway
                DefinedBridge definedBridge = definedTemplateLoaded.getDefinedBridgeByName(recordValue.getColumnName());

                if (definedBridge != null) { // Check that the column is a defined template column, otherwise ignore
                    Entry entry = dbRecord.get(definedBridge.getDefinition());
                    if (entry == null) {
                        entry = new Entry(dbRecord, definedBridge.getDefinition());
                        dbRecord.set(entry);
                    }
                    EntryValue entryValue = entry.get();
                    entryValue.setValue(recordValue.getValue());
                }
            }
        }


        dbRecord.save(RecordState.RAW);
        Infer.me(dbRecord);
    }
}
