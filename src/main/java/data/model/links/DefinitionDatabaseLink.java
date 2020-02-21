package data.model.links;

import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.Definition.class,
        tableName = "definition"
)
public class DefinitionDatabaseLink extends DatabaseLink {
    public DefinitionDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("name", method("getName"), method("setName", String.class)); // 2
        link("calculated", method("isCalculated"), method("setCalculated", Boolean.class)); // 3
    }
}
