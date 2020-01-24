import clarity.Entry;
import clarity.Infer;
import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import log.AppLogger;
import org.apache.log4j.Logger;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        Definition.define().name("D");
        Definition.define().name("E");
        Definition.define().name("A").formula("1+12");
        Definition.define().name("C").formula("[E]*[D]");
        Definition.define().name("B").formula("[C]*[C]");

        Definitions definitions = Definitions.getInstance();
        RecordDefinition.define().name("Employee")
                .addDefinition(definitions.findDefinition("A"))
                .addDefinition(definitions.findDefinition("C"))
                .addDefinition(definitions.findDefinition("D"))
                .addDefinition(definitions.findDefinition("B"))
                .addDefinition(definitions.findDefinition("E"));

        Record record = new Record("Employee");
        record.set(Entry.create("D", "310.34"));
        record.set(Entry.create("E", "4.2"));

        Record record2 = new Record("Employee");
        record2.set(Entry.create("D", "100.654"));
        record2.set(Entry.create("E", "4"));

        Infer.infer();

        log.info("Record 1");
        log.info("A = " + record.get("A").get().getValue());
        log.info("B = " + record.get("B").get().getValue());
        log.info("C = " + record.get("C").get().getValue());

        log.info("Record 2");
        log.info("A = " + record2.get("A").get().getValue());
        log.info("B = " + record2.get("B").get().getValue());
        log.info("C = " + record2.get("C").get().getValue());
    }
}
