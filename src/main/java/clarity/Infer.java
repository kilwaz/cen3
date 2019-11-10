package clarity;

import clarity.load.store.StoredItem;
import clarity.load.store.StoredRecord;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infer {
    private static Logger log = AppLogger.logger();
    private static Infer instance;

    private List<StoredItem> unCalculatedValues = new ArrayList<>();

    private Infer() {

    }

    private void addUnCalculatedValue(StoredItem storedItem) {
        unCalculatedValues.add(storedItem);
    }

    private void calculateValues() {
        log.info("Unfresh items = " + unCalculatedValues.size());

        Pattern pattern = Pattern.compile("\\[(.*?)\\]");

        List<StoredItem> toProcessValues = new ArrayList<>();
        toProcessValues.addAll(unCalculatedValues);

        for (StoredItem storedItem : toProcessValues) {
            StoredRecord storedRecord = storedItem.getStoredRecord();

            String itemFormula = storedItem.getDataItem().getFormula();

            String finalFormula = itemFormula;
            Matcher m = pattern.matcher(itemFormula);
            while (m.find()) {
                String s = m.group(1);

                if (s != null && !s.isEmpty()) {
                    int id = Integer.parseInt(s);
                    StoredItem foundItem = storedRecord.getStoredItem(id);
                    if (foundItem != null) {
                        finalFormula = finalFormula.replace("[" + id + "]", "" + foundItem.getValue());
                    }
                }
            }

            Expression e = new Expression(finalFormula);
            double result = e.calculate();

            storedItem.value(result);
            storedItem.fresh(true);
            unCalculatedValues.remove(storedItem);
        }

        log.info("Infer finished");
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

    public static void me(StoredItem storedItem) {
        Infer.getInstance().addUnCalculatedValue(storedItem);
    }
}
