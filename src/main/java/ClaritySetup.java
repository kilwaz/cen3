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
        Definition.define().name("A").formula("1+12");
        Definition.define().name("C").formula("3+[D]");
        Definition.define().name("D");

        Definitions definitions = Definitions.getInstance();
        RecordDefinition.define().name("Emp")
                .addDefinition(definitions.findDefinition("A"))
                .addDefinition(definitions.findDefinition("C"))
                .addDefinition(definitions.findDefinition("D"));

        Record record = new Record("Emp");
        record.set(new Entry(record, "D", 1));

        Entry entryD = record.get("D");
        Entry entryC = record.get("C");
        log.info("D = " + entryD.get().getValue());

        Infer.infer();

        log.info("C = " + entryC.get().getValue());
    }
}
