package data.model.links;

import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.RecordDefinition.class,
        tableName = "record_definition"
)
public class RecordDefinitionDatabaseLink extends DatabaseLink {
    public RecordDefinitionDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("name", method("getName"), method("name", String.class)); // 2
    }
}
