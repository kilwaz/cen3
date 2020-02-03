import clarity.Entry;
import clarity.Infer;
import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.RecordDefinition;
import log.AppLogger;
import org.apache.log4j.Logger;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        Definition.define().name("A");
        Definition.define().name("B");
        Definition.define().name("C").formula("concat([A],' - ',[B])");
        Definition.define().name("U").formula("upper([C])");
        Definition.define().name("L").formula("lower([C]");
//        Definition.define().name("Number").formula("4");
//
//        RecordDefinition.define().name("Employee").addDefinitions("A", "B", "C", "D", "E", "Text", "Number");
        RecordDefinition.define().name("Employee").addDefinitions("C","U","L","A","B");
//
        Record record = new Record("Employee");
        record.set(Entry.create("A", "Alex"));
        record.set(Entry.create("B", "Brown"));
//        record.set(Entry.create("D", "310.34"));
//        record.set(Entry.create("E", "4.2"));
//
//        Record record2 = new Record("Employee");
//        record2.set(Entry.create("D", "100.654"));
//        record2.set(Entry.create("E", "4"));
//
        Infer.infer();
//
        log.info("Record 1");
        log.info("A = " + record.get("A").get().getValue());
        log.info("B = " + record.get("B").get().getValue());
        log.info("C to concat = " + record.get("C").get().getValue());
        log.info("U to upper = " + record.get("U").get().getValue());
        log.info("L to lower = " + record.get("L").get().getValue());

//        log.info("B = " + record.get("B").get().getValue());
//        log.info("C = " + record.get("C").get().getValue());
//        log.info("Text = " + record.get("Text").get().getValue());
//        log.info("Number = " + record.get("Number").get().getValue());
//
//        log.info("Record 2");
//        log.info("A = " + record2.get("A").get().getValue());
//        log.info("B = " + record2.get("B").get().getValue());
//        log.info("C = " + record2.get("C").get().getValue());
//        log.info("Text = " + record2.get("Text").get().getValue());
//        log.info("Number = " + record2.get("Number").get().getValue());
    }
}
