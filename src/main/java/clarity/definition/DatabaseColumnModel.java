package clarity.definition;

public class DatabaseColumnModel {
    private String columnName;
    private String columnType;

    public DatabaseColumnModel(String columnName, String columnType) {
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }
}
