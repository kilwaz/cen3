package data.model.links;

import clarity.definition.Definition;
import clarity.definition.RecordDefinition;
import clarity.load.excel.DefinedTemplate;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.load.excel.DefinedBridge.class,
        tableName = "defined_bridge"
)
public class DefinedBridgeDatabaseLink extends DatabaseLink {
    public DefinedBridgeDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("column_title", method("getColumnTitle"), method("columnTitle", String.class)); // 2
        link("definition_id", method("getDefinitionUUID"), method("definition", Definition.class)); // 3
        link("record_definition_id", method("getRecordDefinitionUUID"), method("recordDefinition", RecordDefinition.class)); // 4
        link("defined_template_id", method("getDefinedTemplateUUID"), method("definedTemplate", DefinedTemplate.class)); // 5
    }
}
