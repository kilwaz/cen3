package clarity;

import clarity.definition.Definition;
import clarity.load.store.expression.InstancedFormula;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Infer {
    private static Logger log = AppLogger.logger();
    private static Infer instance;
    private List<Entry> unCalculatedEntries = new ArrayList<>();

    private Infer() {

    }

    public void setToCalculate(Entry entry) {
        unCalculatedEntries.add(entry);
    }

    private void calculateValues() {
        log.info(unCalculatedEntries.size() + " entries to infer");
        for (Entry entry : unCalculatedEntries) {
            Definition definition = entry.getDefinition();
            if (definition.isCalculated()) {
                InstancedFormula instancedFormula = definition.getFormula()
                        .createInstance()
                        .record(entry.getRecord());


            } else { // Non calculated entries are always fresh
                entry.setFresh();
            }
        }
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
        Infer.getInstance().setToCalculate(entry);
        entry.setStale();
    }
}
