package clarity.definition;

public class DatabaseColumnModel {
    private final String columnName;
    private final String columnType;
    private final Boolean isLinkColumn;

    public DatabaseColumnModel(String columnName, String columnType) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isLinkColumn = false;
    }

    public DatabaseColumnModel(String columnName, String columnType, Boolean isLinkColumn) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isLinkColumn = isLinkColumn;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public Boolean isLinkColumn() {
        return isLinkColumn;
    }
}
