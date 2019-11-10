package clarity.load.data;

public class Value {
    public static final int TYPE_STRING = 0;
    public static final int TYPE_DOUBLE = 1;

    private int type;
    private int column;
    private int row;

    public Value(int type, int column, int row) {
        this.type = type;
        this.column = column;
        this.row = row;
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
}
