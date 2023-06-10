import clarity.Infer;
import clarity.definition.Definitions;
import clarity.definition.HierarchyTree;
import clarity.load.Load;
import data.ProcedureQuery;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import data.model.dao.HierarchyTreeDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.io.File;
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

//        DefinedMatrix countryMatrix = DefinedMatrix.define().name("Country");
//        countryMatrix.addItem(new MatrixEntry("GBR", "United Kingdom"));
//        countryMatrix.addItem(new MatrixEntry("USA", "United States"));
//        countryMatrix.addItem(new MatrixEntry("ESP", "Spain"));

        setupDB();

        File fileJson = new File("C:\\Users\\alex\\Downloads\\Arup HR Roles 2023\\Uploads\\configjson.json");
        Load.configJSON(fileJson).process();

        Definitions.getInstance().rebuild();

        Infer.infer();

//        Record record = Record.create("Employee");
//        List<Entry> entries = new ArrayList<>();
//        entries.add(Entry.create("A", "aLExAnder"));
//        entries.add(Entry.create("B", "bROwn"));
//        entries.add(Entry.create("ID", "1"));
//        record.set(entries);
//        record.save();
//        Infer.me(record);
//
//        Record record2 = Record.create("Employee");
//        entries = new ArrayList<>();
//        entries.add(Entry.create("A", "Sam"));
//        entries.add(Entry.create("B", "Dude"));
//        entries.add(Entry.create("ID", "2"));
//        record2.set(entries);
//        record2.save();
//        Infer.me(record2);

//        Record user1 = Record.create("User");
//        entries = new ArrayList<>();
//        entries.add(Entry.create("USER_Username", "alex@spl.com"));
//        entries.add(Entry.create("USER_Password", "hello"));
//        entries.add(Entry.create("USER_ID", "1"));
//        user1.set(entries);
//        user1.save();
//
//        Record bonusRecord = Record.create("Bonus");
//        entries = new ArrayList<>();
//        entries.add(Entry.create("D", "1"));
//        entries.add(Entry.create("E", "GBP"));
//        bonusRecord.set(entries);
//        bonusRecord.save();

//        record.addChild(bonusRecord);
//        user1.addChild(bonusRecord); // This should throw and error

//        Duration gap = Duration.ofSeconds(10).plus(Duration.ofMinutes(2));

//        Infer.infer();

//        List<Record> empRecords = DatabaseCollect
//                .create()
//                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
//                .state(RecordState.RAW)
//                .collect();

//        List<Record> records = Records.getInstance().findRecords("A", "aLExAnder");
//        Infer.me(records.get(0));

//        Record bonusTest = records.get(0).getChildren(Definitions.getInstance().findRecordDefinition("Bonus")).get(0);

        File fileExcel = new File("C:\\Users\\alex\\Downloads\\Arup HR Roles 2023\\Uploads\\Test.xlsx");
        Load.excel(fileExcel).process();

        HierarchyTreeDAO hierarchyTreeDAO = new HierarchyTreeDAO();
        HierarchyTree hierarchyTree = hierarchyTreeDAO.getHierarchyTreeByName("ARUP");

        ProcedureQuery procedureQuery = new ProcedureQuery("{ call tree_populate(?)}");
        procedureQuery.addParameter(hierarchyTree.getUuidString());
        procedureQuery.execute();

        log.info("Ready!");
    }

    private static void setupDB() {
//        Definition.define("Employee_Number");
//        Definition.define("First_Name");
//        Definition.define("Preferred_Name");
//        Definition.define("Last_Name");
//        Definition.define("Assignment_Status");
//        Definition.define("Grade");
//        Definition.define("Employment_Category");
//        Definition.define("Office_Location");
//        Definition.define("Currency");
//        Definition.define("Salary");

//        Definition.define("New_Salary").expression("[Salary]+[Increase]");
//        Definition.define("Increase").expression("[Salary]*0.15");
//        Definition.define("E");
//        Definition.define("C").expression("concat([First_Name],' ',[Last_Name])");
//        Definition.define("SPL").expression("((25.0016/24.04)-1)*100");
//        Definition.define("Upper").expression("upper([C])");
//        Definition.define("Lower").expression("lower([C])");
//        Definition.define("Min").expression("min(1,2,3,4)");
//        Definition.define("Max").expression("max(1,2,3,4)");
//        Definition.define("Sum").expression("sum(1,2,3,4)");
//        Definition.define("Average").expression("average(1,2,3,4)");
//        Definition.define("Count").expression("count(1,2,3,4)");
//        Definition.define("Proper").expression("proper([C])");
//        Definition.define("Equals").expression("1=1");
//        Definition.define("Length").expression("len([Sum])");
//        Definition.define("Equals_2").expression("2.00003=2.0000301");
//        Definition.define("Greater").expression("'B'>'A'");
//        Definition.define("Less").expression("1>2");
//        Definition.define("Round").expression("round(10.12545,4)");
//        Definition.define("If").expression("if([Less],[Average],[SPL])");
//        Definition.define("Num").expression("3.3");
//        Definition.define("Num_2").expression("3.7*10");
//        Definition.define("Matrix").expression("matrix('Country','USA')");

//        RecordDefinition employee = RecordDefinition.define("Employee");
//        employee.primaryKey(Definitions.getInstance().findDefinition("ID"));
//        employee.primaryKey(Definitions.getInstance().findDefinition("Employee_Number"));
//        employee.addDefinitions("Employee_Number", "First_Name", "Preferred_Name", "Last_Name", "C", "Upper", "Lower", "Proper", "Assignment_Status", "Grade", "Office_Location", "Employment_Category", "Currency", "Salary", "Increase");
//        employee.addDefinitions("ID", "C", "Upper", "Lower", "A", "B", "Min", "Max", "Sum", "Proper",
//                "Count", "Average", "Equals", "Equals_2", "Length", "Greater", "Less", "Round", "If", "Matrix", "Num", "Num_2", "SPL");

//        RecordDefinition bonus = RecordDefinition.define("Bonus").addDefinitions("D", "E", "Max");
//        employee.defineChildRecordDefinition(bonus);
//
//        Definition.define("USER_ID");
//        Definition.define("USER_Username");
//        Definition.define("USER_Password");
//
//        RecordDefinition.define("User").addDefinitions("USER_ID", "USER_Username", "USER_Password");
    }

    public static void clearDatabase() {
        List<String> systemTables = new ArrayList<>();
        // Definitions
        systemTables.add("definition".toUpperCase());
        systemTables.add("definition_group".toUpperCase());
        systemTables.add("record_definition".toUpperCase());
        systemTables.add("record_definition_child".toUpperCase());

        // Imports
        systemTables.add("defined_template".toUpperCase());
        systemTables.add("defined_bridge".toUpperCase());

        systemTables.add("worksheet_config".toUpperCase());

        // Hierarchy
        systemTables.add("hierarchy_trees".toUpperCase());
        systemTables.add("hierarchy_nodes".toUpperCase());
        systemTables.add("hierarchy_nodes_calc".toUpperCase());
        systemTables.add("hierarchy".toUpperCase());

        // Event Log
        systemTables.add("event_log".toUpperCase());

//        SelectQuery selectQuery = new SelectQuery("show tables");  // MySQL way
        SelectQuery selectQuery = new SelectQuery("SELECT table_name FROM dba_tables where owner = 'CEN'"); // Oracle way
        SelectResult result = (SelectResult) selectQuery.execute();
        for (SelectResultRow selectResultRow : result.getResults()) {
            String tableName = selectResultRow.getString("table_name");
            if (!systemTables.contains(tableName)) {
                log.info("Deleting " + tableName);
                new SelectQuery("truncate table " + tableName).execute();
                new SelectQuery("drop table " + tableName).execute();
            }
        }

        for (String tableName : systemTables) {
            new SelectQuery("truncate table " + tableName).execute();
        }
    }
}
