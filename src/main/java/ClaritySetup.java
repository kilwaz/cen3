import clarity.Entry;
import clarity.Infer;
import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import clarity.load.store.DefinedMatrix;
import clarity.load.store.MatrixEntry;
import clarity.load.store.Records;
import core.builders.requests.WebSocketMessageMapping;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    private static Boolean clearDownDatabase = true;

    public static void main(String[] args) {
        ApplicationInitialiser.init(); // Connects to the database/inits web sockets
        if (clearDownDatabase) {
            clearDatabase();
        }
        Definitions.getInstance(); // Load in data from database

        DefinedMatrix countryMatrix = DefinedMatrix.define().name("Country");
        countryMatrix.addItem(new MatrixEntry("GBR", "United Kingdom"));
        countryMatrix.addItem(new MatrixEntry("USA", "United States"));
        countryMatrix.addItem(new MatrixEntry("ESP", "Spain"));

        setupDB();

        Definitions.getInstance().rebuild();

        Infer.infer();

        Record record = Record.create("Employee");

        List<Entry> entries = new ArrayList<>();
        entries.add(Entry.create("A", "aLExAnder"));
        entries.add(Entry.create("B", "bROwn"));
        entries.add(Entry.create("ID", "1"));
        record.set(entries);
        record.save();

        Record record2 = Record.create("Employee");
        entries = new ArrayList<>();
        entries.add(Entry.create("A", "Sam"));
        entries.add(Entry.create("B", "Dude"));
        entries.add(Entry.create("ID", "2"));
        record2.set(entries);
        record2.save();

        Record user1 = Record.create("User");
        entries = new ArrayList<>();
        entries.add(Entry.create("USER_Username", "alex@spl.com"));
        entries.add(Entry.create("USER_Password", "hello"));
        entries.add(Entry.create("USER_ID", "1"));
        user1.set(entries);
        user1.save();

        Record bonusRecord = Record.create("Bonus");
        entries = new ArrayList<>();
        entries.add(Entry.create("D", "1"));
        entries.add(Entry.create("E", "GBP"));
        bonusRecord.set(entries);
        bonusRecord.save();

        record.addChild(bonusRecord);
        user1.addChild(bonusRecord);

        Duration gap = Duration.ofSeconds(10).plus(Duration.ofMinutes(2));

        Infer.infer();

        List<Record> records = Records.getInstance().findRecords("A", "aLExAnder");
        Record bonusTest = records.get(0).getChildren(Definitions.getInstance().findRecordDefinition("Bonus")).get(0);

        log.info("Ready!");
    }

    private static void setupDB() {
        Definition.define("ID");
        Definition.define("A");
        Definition.define("B");
        Definition.define("D");
        Definition.define("E");
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

        RecordDefinition employee = RecordDefinition.define("Employee").addDefinitions("ID", "C", "U", "L", "A", "B", "Min", "Max", "Sum", "Proper",
                "Count", "Average", "Equals", "Equals 2", "Length", "Greater", "Less", "Round", "If", "Matrix", "Num", "SPL");

        RecordDefinition bonus = RecordDefinition.define("Bonus").addDefinitions("D", "E", "Max");
        employee.defineChildRecordDefinition(bonus);

        Definition.define("USER_ID");
        Definition.define("USER_Username");
        Definition.define("USER_Password");

        RecordDefinition.define("User").addDefinitions("USER_ID", "USER_Username", "USER_Password");
    }

    public static void clearDatabase() {
        SelectQuery selectQuery = new SelectQuery("show tables");
        SelectResult result = (SelectResult) selectQuery.execute();
        for (SelectResultRow selectResultRow : result.getResults()) {
            String tableName = selectResultRow.getString("Tables_in_clarity");
            if (!"definition_group".equals(tableName) && !"record_definition".equals(tableName) && !"definition".equals(tableName) && !"record_definition_child".equals(tableName)) {
                log.info("Deleting " + tableName);
                new SelectQuery("truncate table " + tableName).execute();
                new SelectQuery("drop table " + tableName).execute();
            }
        }
        new SelectQuery("truncate table definition_group").execute();
        new SelectQuery("truncate table record_definition").execute();
        new SelectQuery("truncate table definition").execute();
        new SelectQuery("truncate table record_definition_child").execute();
    }
}
