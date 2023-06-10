package clarity;

import clarity.definition.Definition;
import clarity.definition.Hierarchy;
import clarity.definition.HierarchyTree;
import clarity.definition.RecordState;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.instance.InstancedFormula;
import data.model.dao.HierarchyTreeDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

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
        HierarchyTree hierarchyTree = new HierarchyTreeDAO().getHierarchyTreeByName("ARUP");

        for (Definition definition : definitions) {
            if (definition.isCalculated() && record.has(definition)) {
                InstancedFormula instancedFormula = definition.getFormula()
                        .createInstance()
                        .record(record);

                Expression solvedExpression = instancedFormula.solve();
                record.get(definition.getName()).get().setValue(solvedExpression.getStringRepresentation());
            }
            if ("Reviewing_Manager_ID".equalsIgnoreCase(definition.getName())) { // Hierarchy linked
                Hierarchy hierarchy = Hierarchy.create(Hierarchy.class);
                hierarchy.employee(record);
                Double value2 = (Double) record.get("Employee_Number").get().getValue();
                hierarchy.nodeReference(String.valueOf(value2.intValue()));
                if (record.has(definition)) { // Check record has this definition
                    Double value = (Double) record.get(definition.getName()).get().getValue();
                    hierarchy.parentReference("N9-" + String.valueOf(value.intValue()));
                }
                hierarchy.hierarchyTree(hierarchyTree);
                hierarchy.save();

                // This is to create N9 nodes in the hierarchy
                hierarchy = Hierarchy.create(Hierarchy.class);
                hierarchy.nodeReference("N9-" + String.valueOf(value2.intValue()));
                if (record.has(definition)) { // Check record has this definition
                    Double value = (Double) record.get(definition.getName()).get().getValue();
                    hierarchy.parentReference("N9-" + String.valueOf(value.intValue()));
                } else {
                    hierarchy.parentReference(hierarchyTree.getName());
                }

                hierarchy.hierarchyTree(hierarchyTree);
                hierarchy.save();
            }
        }

        record.save(RecordState.STATIC);
    }
}
