package data.model.links;

import clarity.definition.Definition;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.WorksheetConfig.class,
        tableName = "worksheet_config"
)
public class WorksheetConfigDatabaseLink extends DatabaseLink {
    public WorksheetConfigDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("column_title", method("getColumnTitle"), method("columnTitle", String.class)); // 2
        link("definition_id", method("getDefinitionUUID"), method("definition", Definition.class)); // 3
        link("column_type", method("getColumnType"), method("columnType", String.class)); // 4
        link("data_type", method("getDataType"), method("dataType", String.class)); // 5
        link("column_order", method("getColumnOrder"), method("columnOrder", Integer.class)); // 6
        link("text_align", method("getTextAlign"), method("textAlign", String.class)); // 7
        link("colour", method("getColour"), method("colour", String.class)); // 8
    }
}
