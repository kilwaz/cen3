package data.model.links;

import clarity.definition.RecordDefinition;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.RecordDefinitionChild.class,
        tableName = "record_definition_child"
)
public class RecordDefinitionChildDatabaseLink extends DatabaseLink {
    public RecordDefinitionChildDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("record_definition_parent_uuid", method("getRecordDefinitionParentUUID"), method("setRecordDefinitionParent", RecordDefinition.class)); // 2
        link("record_definition_child_uuid", method("getRecordDefinitionChildUUID"), method("setRecordDefinitionChild", RecordDefinition.class)); // 3
    }
}
