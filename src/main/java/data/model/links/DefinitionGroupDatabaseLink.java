package data.model.links;

import clarity.definition.Definition;
import clarity.definition.RecordDefinition;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.DefinitionGroup.class,
        tableName = "definition_group"
)
public class DefinitionGroupDatabaseLink extends DatabaseLink {
    public DefinitionGroupDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("definition_id", method("getDefinitionUUID"), method("definition", Definition.class)); // 2
        link("record_definition_id", method("getRecordDefinitionUUID"), method("recordDefinition", RecordDefinition.class)); // 3
    }
}
