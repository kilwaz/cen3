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
        Definition.define().name("Min").formula("min(1,2,3,4)");
        Definition.define().name("Max").formula("max(1,2,3,4)");
        Definition.define().name("Sum").formula("sum(1,2,3,4)");
        Definition.define().name("Average").formula("average(1,2,3,4)");
        Definition.define().name("Count").formula("count(1,2,3,4)");
        Definition.define().name("Proper").formula("proper([C])");
        Definition.define().name("Equals").formula("1=1");
        Definition.define().name("Length").formula("len([Sum])");
        Definition.define().name("Equals 2").formula("2.00003=2.0000301");
        Definition.define().name("Greater").formula("'B'>'A'");
        Definition.define().name("Less").formula("2<1");

        RecordDefinition.define().name("Employee").addDefinitions("C", "U", "L", "A", "B", "Min", "Max", "Sum", "Proper", "Count", "Average", "Equals", "Equals 2", "Length", "Greater", "Less");

        Record record = new Record("Employee");
        record.set(Entry.create("A", "aLExAnder"));
        record.set(Entry.create("B", "bROwn"));

        Infer.infer();

        log.info("Record 1");
        log.info("A = " + record.get("A").get().getValue());
        log.info("B = " + record.get("B").get().getValue());
        log.info("C to concat = " + record.get("C").get().getValue());
        log.info("U to upper = " + record.get("U").get().getValue());
        log.info("L to lower = " + record.get("L").get().getValue());
        log.info("Min = " + record.get("Min").get().getValue());
        log.info("Max = " + record.get("Max").get().getValue());
        log.info("Sum = " + record.get("Sum").get().getValue());
        log.info("Proper = " + record.get("Proper").get().getValue());
        log.info("Count = " + record.get("Count").get().getValue());
        log.info("Average = " + record.get("Average").get().getValue());
        log.info("Equals = " + record.get("Equals").get().getValue());
        log.info("Length = " + record.get("Length").get().getValue());
        log.info("Equals 2 = " + record.get("Equals 2").get().getValue());
        log.info("Greater = " + record.get("Greater").get().getValue());
        log.info("Less = " + record.get("Less").get().getValue());

    }
}
