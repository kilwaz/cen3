package clarity.load.store;

public class MatrixEntry {
    private String key;
    private String value;

    public MatrixEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public MatrixEntry value(String value) {
        this.value = value;
        return this;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
