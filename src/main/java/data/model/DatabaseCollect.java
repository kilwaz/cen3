package data.model;

import clarity.Record;
import clarity.definition.Definition;
import clarity.definition.HierarchyNode;
import clarity.definition.RecordDefinition;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseCollect {
    private static Logger log = AppLogger.logger();

    private RecordDefinition recordDefinition;
    private int state;
    private String primaryKey; // Confusing name?
    private String uuid;
    private List<Definition> definitions = null;
    private DatabaseSortFilter databaseSortFilter = null;
    private String nodeReference;
    private Integer pageNumber;
    private Integer pageSize;

    private DatabaseCollect() {

    }

    public static DatabaseCollect create() {
        return new DatabaseCollect();
    }

    public DatabaseCollect withDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
        return this;
    }

    public DatabaseCollect recordDefinition(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
        return this;
    }

    public DatabaseCollect state(int state) {
        this.state = state;
        return this;
    }

    public DatabaseCollect uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public DatabaseCollect nodeReference(HierarchyNode hierarchyNode) {
        if (hierarchyNode != null) {
            this.nodeReference = hierarchyNode.getNodeReference();
        }
        return this;
    }

    public DatabaseCollect nodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
        return this;
    }

    public DatabaseCollect sortFilter(DatabaseSortFilter databaseSortFilter) {
        this.databaseSortFilter = databaseSortFilter;
        return this;
    }

    public DatabaseCollect primaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    public DatabaseCollect pageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public DatabaseCollect pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public List<Record> collect() {
        if (recordDefinition == null) {
            log.info("Can't collect due to null requirements");
            return null;
        }

        List<Record> resultList = new ArrayList<>();

        // Building the select query string
        var selectQueryBuilder = new StringBuilder();
        selectQueryBuilder.append("select res.uuid from ").append(recordDefinition.getTableNameByState(state)).append(" res");
        if (nodeReference != null) {
            selectQueryBuilder.append(" left join hierarchy_nodes hn on (hn.employee_uuid = res.uuid) where hn.parent_reference = '").append(nodeReference).append("' and hn.node_type = 'Employee' ");
        }
        if (primaryKey != null) {
            selectQueryBuilder.append(" where ").append(recordDefinition.getPrimaryKey().getName()).append(" = '").append(primaryKey).append("'");
        } else if (uuid != null) {
            selectQueryBuilder.append(" where uuid = '").append(uuid).append("'");
        }

        // Apply any sorting
        if (databaseSortFilter != null) {
            List<DatabaseSort> databaseSorts = databaseSortFilter.getSorts();
            if (databaseSorts.size() > 0) {
                selectQueryBuilder.append(" order by ");
                int orderCounter = 0;
                for (DatabaseSort databaseSort : databaseSorts) {
                    Definition definition = recordDefinition.getDefinition(databaseSort.getDefinition());
                    if (orderCounter > 0) {
                        selectQueryBuilder.append(",");
                    }
                    selectQueryBuilder.append("res.").append(definition.getName()).append(" ");
                    if ("desc".equalsIgnoreCase(databaseSort.getDirection())) {
                        selectQueryBuilder.append("desc ");
                    }

                    orderCounter++;
                }
            }
        }

        var selectResult = (SelectResult) new SelectQuery(selectQueryBuilder.toString()).execute();
        List<SelectResultRow> results = selectResult.getResults();
        Integer startRange = 0;
        Integer endRange = results.size();
        if (pageSize != null && pageNumber != null) {
            startRange = (pageNumber * pageSize) - pageSize;
            endRange = pageNumber * pageSize;
            if (endRange > results.size()) {
                endRange = results.size();
            }

            log.info("Start range = " + startRange);
            log.info("End range = " + endRange);
        }

        if (results.size() > 0) {
            for (int i = startRange; i < endRange; i++) {
                String uuid = results.get(i).getString("UUID");
                Record loadedRecord = Record.load(recordDefinition, UUID.fromString(uuid));
                loadedRecord.load(state);
                resultList.add(loadedRecord);
            }
        }

        return resultList;
    }

    public Record singleResult() {
        List<Record> results = collect();

        if (results.size() == 0) {
            return null;
        } else {
            return results.get(0);
        }
    }
}