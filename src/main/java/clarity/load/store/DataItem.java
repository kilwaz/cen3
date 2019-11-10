package clarity.load.store;

public class DataItem {
    public static final Integer TYPE_STATIC = 0;
    public static final Integer TYPE_CALCULATED = 1;

    private Integer id;
    private Integer type;
    private String formula;

    public DataItem(Integer id, Integer type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }
}
