package clarity;

import clarity.definition.Definition;
import clarity.definition.RecordState;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.instance.InstancedFormula;
import data.model.DatabaseAction;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.internal.database.base.Database;

import java.util.ArrayList;
import java.util.List;

public class Infer {
    private static Logger log = AppLogger.logger();
    private static Infer instance;
//    private List<Entry> unCalculatedEntries = new ArrayList<>();

    private Infer() {

    }

//    public void setToCalculate(Entry entry) {
//        unCalculatedEntries.add(entry);
//    }

    private void calculateValues() {
//        List<Entry> unCalculatedEntriesToProcess = new ArrayList<>(unCalculatedEntries);
//        for (Entry entry : unCalculatedEntriesToProcess) {
//            Definition definition = entry.getDefinition();
//            if (definition.isCalculated()) {
//                // After using this instance formula it isn't saved or reused yet
//                InstancedFormula instancedFormula = definition.getFormula()
//                        .createInstance()
//                        .record(entry.getRecord());
//
//                Expression solvedExpression = instancedFormula.solve();
//                entry.set(new EntryValue(solvedExpression));
//            }
//            entry.setFresh();
//            if (entry.isFresh()) {
//                unCalculatedEntries.remove(entry);
//            }
//        }
    }

    public static void infer() {
        Infer.getInstance().calculateValues();
    }

    public static Infer getInstance() {
        if (instance == null) {
            instance = new Infer();
        }
        return instance;
    }

    public static void me(Entry entry) {
//        Infer.getInstance().setToCalculate(entry);
//        entry.setStale();
    }

    public static void me(Record record) {
        List<Definition> definitions = record.getRecordDefinition().getDefinitions();
        for (Definition definition : definitions) {
            if (definition.isCalculated()) {
                InstancedFormula instancedFormula = definition.getFormula()
                        .createInstance()
                        .record(record);

                Expression solvedExpression = instancedFormula.solve();
                record.get(definition.getName()).get().setValue(solvedExpression.getStringRepresentation());
            }
        }

        record.save(RecordState.STATIC);
    }
}
