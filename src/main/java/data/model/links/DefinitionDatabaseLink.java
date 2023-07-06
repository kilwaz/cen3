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
        link("name", method("getName"), method("name", String.class)); // 2
        link("expression", method("getExpression"), method("expression", String.class)); // 3
        link("calculated", method("isCalculated"), method("calculated", Boolean.class)); // 4
        link("definition_type", method("getDefinitionType"), method("definitionType", Integer.class)); // 5
        link("context_type", method("getContextType"), method("contextType", Integer.class)); // 6
    }
}
