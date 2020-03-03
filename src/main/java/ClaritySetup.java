import clarity.Entry;
import clarity.Infer;
import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import clarity.load.store.DefinedMatrix;
import clarity.load.store.MatrixEntry;
import log.AppLogger;
import org.apache.log4j.Logger;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        ApplicationInitialiser.init(); // Connects to the database/inits web sockets
        Definitions.getInstance(); // Load in data from database

        DefinedMatrix countryMatrix = DefinedMatrix.define().name("Country");
        countryMatrix.addItem(new MatrixEntry("GBR", "United Kingdom"));
        countryMatrix.addItem(new MatrixEntry("USA", "United States"));
        countryMatrix.addItem(new MatrixEntry("ESP", "Spain"));

        setupDB();

        Record record = new Record("Employee");
        record.set(Entry.create("A", "aLExAnder"));
        record.set(Entry.create("B", "bROwn"));
        record.set(Entry.create("ID", "1"));

        Record record2 = new Record("Employee");
        record2.set(Entry.create("A", "Sam"));
        record2.set(Entry.create("B", "Dude"));
        record2.set(Entry.create("ID", "2"));

        Infer.infer();

        Definitions.getInstance().getRecordDefinitionHashMap();
    }

    private static void setupDB() {
        Definition.define("ID");
        Definition.define("A");
        Definition.define("B");
        Definition.define("C").expression("coNCat([A],' - ',[B])");
        Definition.define("SPL").expression("((25.0016/24.04)-1)*100");
        Definition.define("U").expression("upper([C])");
        Definition.define("L").expression("lower([C]");
        Definition.define("Min").expression("min(1,2,3,4)");
        Definition.define("Max").expression("max(1,2,3,4)");
        Definition.define("Sum").expression("sum(1,2,3,4)");
        Definition.define("Average").expression("average(1,2,3,4)");
        Definition.define("Count").expression("count(1,2,3,4)");
        Definition.define("Proper").expression("proper([C])");
        Definition.define("Equals").expression("1=1");
        Definition.define("Length").expression("len([Sum])");
        Definition.define("Equals 2").expression("2.00003=2.0000301");
        Definition.define("Greater").expression("'B'>'A'");
        Definition.define("Less").expression("1>2");
        Definition.define("Round").expression("round(10.12545,4)");
        Definition.define("If").expression("if([Less],[Average],[SPL])");
        Definition.define("Num").expression("3.3");
        Definition.define("Matrix").expression("matrix('Country','USA')");

        RecordDefinition.define("Employee").addDefinitions("ID", "C", "U", "L", "A", "B", "Min", "Max", "Sum", "Proper",
                "Count", "Average", "Equals", "Equals 2", "Length", "Greater", "Less", "Round", "If", "Matrix", "Num", "SPL");

    }
}
