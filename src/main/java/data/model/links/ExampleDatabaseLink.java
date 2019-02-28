package data.model.links;


import data.model.DatabaseLink;
import data.model.objects.ExampleObject;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = ExampleObject.class,
        tableName = "example"
)
public class ExampleDatabaseLink extends DatabaseLink {
    public ExampleDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("value", method("getValue"), method("setValue", String.class)); // 2
    }
}