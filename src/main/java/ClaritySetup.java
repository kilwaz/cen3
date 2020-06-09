import clarity.Entry;
import clarity.Infer;
import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import clarity.load.store.DefinedMatrix;
import clarity.load.store.MatrixEntry;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClaritySetup {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        ApplicationInitialiser.init(); // Connects to the database/inits web sockets
        //clearDatabase();
        Definitions.getInstance(); // Load in data from database

        DefinedMatrix countryMatrix = DefinedMatrix.define().name("Country");
        countryMatrix.addItem(new MatrixEntry("GBR", "United Kingdom"));
        countryMatrix.addItem(new MatrixEntry("USA", "United States"));
        countryMatrix.addItem(new MatrixEntry("ESP", "Spain"));

        setupDBIceCream();
        setupDB();

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
        entries.add(Entry.create("USER_Password", "Hello"));
        entries.add(Entry.create("USER_ID", "1"));
        user1.set(entries);
        user1.save();

        Duration gap = Duration.ofSeconds(10).plus(Duration.ofMinutes(2));

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

        Definition.define("USER_ID");
        Definition.define("USER_Username");
        Definition.define("USER_Password");

        RecordDefinition.define("User").addDefinitions("USER_ID", "USER_Username", "USER_Password");
    }

    private static void setupDBIceCream() {
        // Person
        Definition.define("PERSON_ID").asTypeNumber();
        Definition.define("PERSON_FIRST_NAME").asTypeString();
        Definition.define("PERSON_LAST_NAME").asTypeString();
        Definition.define("PERSON_GENDER").asTypeString();

        // Video
        Definition.define("VIDEO_ID");
        Definition.define("VIDEO_NAME");
        Definition.define("VIDEO_LENGTH");
        Definition.define("VIDEO_DATE");

        // Ice Cream
        Definition.define("ICE_CREAM_ID");
        Definition.define("ICE_CREAM_OPENED");
        Definition.define("ICE_CREAM_DEAD");

        // Session
        Definition.define("SESSION_ID");
        Definition.define("SESSION_ICE_CREAM_ID");
        Definition.define("SESSION_PERSON_1");
        Definition.define("SESSION_PERSON_2");
        Definition.define("SESSION_THRUSTS");
        Definition.define("SESSION_BRUTALITY_RATING");
        Definition.define("SESSION_START");
        Definition.define("SESSION_END");

        RecordDefinition.define("IceCream").addDefinitions("ICE_CREAM_ID", "ICE_CREAM_OPENED", "ICE_CREAM_DEAD");
        RecordDefinition.define("Person").addDefinitions("PERSON_ID", "PERSON_FIRST_NAME", "PERSON_LAST_NAME", "PERSON_GENDER");
        RecordDefinition.define("Video").addDefinitions("VIDEO_ID", "VIDEO_NAME", "VIDEO_LENGTH", "VIDEO_DATE");
        RecordDefinition.define("Session").addDefinitions("SESSION_ID", "SESSION_ICE_CREAM_ID", "SESSION_PERSON_1", "SESSION_PERSON_2", "SESSION_THRUSTS", "SESSION_BRUTALITY_RATING", "SESSION_START", "SESSION_END");
    }

    public static void clearDatabase() {
        SelectQuery selectQuery = new SelectQuery("show tables");
        SelectResult result = (SelectResult) selectQuery.execute();
        for (SelectResultRow selectResultRow : result.getResults()) {
            String tableName = selectResultRow.getString("Tables_in_clarity");
            if (!"definition_group".equals(tableName) && !"record_definition".equals(tableName) && !"definition".equals(tableName)) {
                log.info(tableName);
                new SelectQuery("truncate table " + tableName).execute();
                new SelectQuery("drop table " + tableName).execute();
            }
        }
        new SelectQuery("truncate table definition_group").execute();
        new SelectQuery("truncate table record_definition").execute();
        new SelectQuery("truncate table definition").execute();
    }
}
