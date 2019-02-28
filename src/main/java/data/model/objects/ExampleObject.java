package data.model.objects;

import data.model.DatabaseObject;
import org.apache.log4j.Logger;

import java.util.UUID;

public class ExampleObject extends DatabaseObject {
    private static Logger log = Logger.getLogger(ExampleObject.class);

    private String value = "";

    public ExampleObject() {
        super();
    }

    public ExampleObject(UUID uuid, String value) {
        super(uuid);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
