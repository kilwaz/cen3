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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Definition definition = entry.getDefinition();
        if (definition.isCalculated()) {
            InstancedFormula instancedFormula = definition.getFormula()
                    .createInstance()
                    .record(entry.getRecord());

            Expression solvedExpression = instancedFormula.solve();
            entry.getRecord().get(definition.getName()).get().setValue(solvedExpression.getStringRepresentation());
            entry.markAsChanged();
        }

        // Also calculate dependants
        for (Definition def : definition.getDependants()) {
            Entry dependantEntry = entry.getRecord().get(def);
            Infer.me(dependantEntry);
        }
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

                Object empNumber = record.get("Employee_Number").get().getValue();
                String value2 = "";
                if (empNumber instanceof Double) {
                    value2 = "" + ((Double) empNumber).intValue();
                } else if (empNumber instanceof String) {
                    value2 = (String) empNumber;
                }

                hierarchy.nodeReference(value2);
                if (record.has(definition)) { // Check record has this definition
                    Object value = record.get(definition.getName()).get().getValue();
                    String result = "";
                    if (value instanceof Double) {
                        result = "" + ((Double) value).intValue();
                    } else if (value instanceof String) {
                        result = (String) value;
                    }

                    hierarchy.parentReference("N9-" + result);
                }
                hierarchy.hierarchyTree(hierarchyTree);
                hierarchy.save();

                // This is to create N9 nodes in the hierarchy
                hierarchy = Hierarchy.create(Hierarchy.class);
                hierarchy.nodeReference("N9-" + value2);
                if (record.has(definition)) { // Check record has this definition
                    Object value = record.get(definition.getName()).get().getValue();
                    String result = "";
                    if (value instanceof Double) {
                        result = "" + ((Double) value).intValue();
                    } else if (value instanceof String) {
                        result = (String) value;
                    }

                    hierarchy.parentReference("N9-" + result);
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
