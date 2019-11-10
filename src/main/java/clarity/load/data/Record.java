package clarity.load.data;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private List<Value> values = new ArrayList<>();

    public Record() {

    }

    public Record addValue(Double value, int column, int row) {
        values.add(new DoubleValue(value, column, row));
        return this;
    }

    public Record addValue(String value, int column, int row) {
        values.add(new StringValue(value, column, row));
        return this;
    }

    public int getRecordCount() {
        return values.size();
    }

    public List<Value> getValues() {
        return values;
    }
}
