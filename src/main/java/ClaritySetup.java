import clarity.Entry;
import clarity.Infer;
import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.RecordDefinition;
import clarity.load.store.DefinedMatrix;
import clarity.load.store.MatrixEntry;
import data.model.dao.DefinitionDAO;
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

        Definition definitionID = Definition.define().name("ID");
        definitionID.save();

//        Definition definitionID2 = new DefinitionDAO().getDefinitionByName("ID");

        Definition.define().name("A");
        Definition.define().name("B");
        Definition.define().name("C").formula("coNCat([A],' - ',[B])");
        Definition.define().name("SPL").formula("((25.0016/24.04)-1)*100");
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
        Definition.define().name("Less").formula("1>2");
        Definition.define().name("Round").formula("round(10.12545,4)");
        Definition.define().name("If").formula("if([Less],[Average],[SPL])");
        Definition.define().name("Num").formula("3.3");

        Definition.define().name("Matrix").formula("matrix('Country','USA')");

        RecordDefinition.define().name("Employee").addDefinitions("ID", "C", "U", "L", "A", "B", "Min", "Max", "Sum", "Proper",
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
