package data.model.links;

import clarity.definition.Definition;
import clarity.definition.WorksheetConfig;
import clarity.definition.WorksheetConfigDetails;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = WorksheetConfigDetails.class,
        tableName = "worksheet_config_details"
)
public class WorksheetConfigDetailsDatabaseLink extends DatabaseLink {
    public WorksheetConfigDetailsDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("worksheet_config_id", method("getWorksheetConfigUUID"), method("worksheetConfig", WorksheetConfig.class)); // 2
        link("column_title", method("getColumnTitle"), method("columnTitle", String.class)); // 3
        link("definition_id", method("getDefinitionUUID"), method("definition", Definition.class)); // 4
        link("column_type", method("getColumnType"), method("columnType", String.class)); // 5
        link("column_order", method("getColumnOrder"), method("columnOrder", Integer.class)); // 6
        link("text_align", method("getTextAlign"), method("textAlign", String.class)); // 7
        link("colour", method("getColour"), method("colour", String.class)); // 8
    }
}
