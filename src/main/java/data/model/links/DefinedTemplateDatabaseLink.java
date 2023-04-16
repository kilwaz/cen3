package data.model.links;

import clarity.definition.Definition;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.load.excel.DefinedTemplate.class,
        tableName = "defined_template"
)
public class DefinedTemplateDatabaseLink extends DatabaseLink {
    public DefinedTemplateDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("name", method("getName"), method("name", String.class)); // 2
        link("primary_key", method("getPrimaryKeyUUID"), method("primaryKey", Definition.class)); // 3
    }
}
