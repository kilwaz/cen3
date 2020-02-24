import clarity.Entry;
import clarity.Infer;
import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.RecordDefinition;
import clarity.load.store.DefinedMatrix;
import clarity.load.store.MatrixEntry;
import log.AppLogger;
import org.apache.log4j.Logger;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        ApplicationInitialiser.init();

        DefinedMatrix countryMatrix = DefinedMatrix.define().name("Country");
        countryMatrix.addItem(new MatrixEntry("GBR", "United Kingdom"));
        countryMatrix.addItem(new MatrixEntry("USA", "United States"));
        countryMatrix.addItem(new MatrixEntry("ESP", "Spain"));

        Definition.define("ID");

        Definition.define("A");
        Definition.define("B");
        Definition.define("C").formula("coNCat([A],' - ',[B])");
        Definition.define("SPL").formula("((25.0016/24.04)-1)*100");
        Definition.define("U").formula("upper([C])");
        Definition.define("L").formula("lower([C]");
        Definition.define("Min").formula("min(1,2,3,4)");
        Definition.define("Max").formula("max(1,2,3,4)");
        Definition.define("Sum").formula("sum(1,2,3,4)");
        Definition.define("Average").formula("average(1,2,3,4)");
        Definition.define("Count").formula("count(1,2,3,4)");
        Definition.define("Proper").formula("proper([C])");
        Definition.define("Equals").formula("1=1");
        Definition.define("Length").formula("len([Sum])");
        Definition.define("Equals 2").formula("2.00003=2.0000301");
        Definition.define("Greater").formula("'B'>'A'");
        Definition.define("Less").formula("1>2");
        Definition.define("Round").formula("round(10.12545,4)");
        Definition.define("If").formula("if([Less],[Average],[SPL])");
        Definition.define("Num").formula("3.3");

        Definition.define("Matrix").formula("matrix('Country','USA')");

        RecordDefinition.define("Employee").addDefinitions("ID", "C", "U", "L", "A", "B", "Min", "Max", "Sum", "Proper",
                "Count", "Average", "Equals", "Equals 2", "Length", "Greater", "Less", "Round", "If", "Matrix", "Num", "SPL");

        Record record = new Record("Employee");
        record.set(Entry.create("A", "aLExAnder"));
        record.set(Entry.create("B", "bROwn"));
        record.set(Entry.create("ID", "1"));

        Record record2 = new Record("Employee");
        record2.set(Entry.create("A", "Sam"));
        record2.set(Entry.create("B", "Dude"));
        record2.set(Entry.create("ID", "2"));

        Infer.infer();
    }
}
