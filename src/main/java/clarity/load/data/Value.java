package clarity.load.data;

public class Value {
    public static final int TYPE_STRING = 0;
    public static final int TYPE_DOUBLE = 1;

    private int type;
    private int column;
    private int row;
    private String columnName;

    public Value(int type, int column, int row, String columnName) {
        this.type = type;
        this.column = column;
        this.row = row;
        this.columnName = columnName;
    }

    public int getType() {
        return type;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String getColumnName() {
        return columnName;
    }

    public Object getValue() { // Overridden
        return null;
    }
}
