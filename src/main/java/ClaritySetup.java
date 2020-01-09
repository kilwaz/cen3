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
        Definition.define().name("C").formula("[E]+[D]");

        Definitions definitions = Definitions.getInstance();
        RecordDefinition.define().name("Emp")
                .addDefinition(definitions.findDefinition("A"))
                .addDefinition(definitions.findDefinition("C"))
                .addDefinition(definitions.findDefinition("D"))
                .addDefinition(definitions.findDefinition("E"));

        Record record = new Record("Emp");
        record.set(new Entry(record, "D", 300));
        record.set(new Entry(record, "E", 4.2));

        Infer.infer();

        log.info("C = " + record.get("C").get().getValue());
    }
}
