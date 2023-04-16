package clarity.load.data;

import java.util.ArrayList;
import java.util.List;

public class LoadedRecord {
    private List<Value> values = new ArrayList<>();

    public LoadedRecord() {

    }

    public LoadedRecord addValue(Double value, int column, int row, String columnName) {
        values.add(new DoubleValue(value, column, row, columnName));
        return this;
    }

    public LoadedRecord addValue(String value, int column, int row, String columnName) {
        values.add(new StringValue(value, column, row, columnName));
        return this;
    }

    public int getRecordCount() {
        return values.size();
    }

    public List<Value> getValues() {
        return values;
    }

    public Value getValueByColumnNumber(int columnNumber) {
        return values.get(columnNumber);
    }
}
