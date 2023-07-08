package data.model.links;

import clarity.definition.WorksheetConfig;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = WorksheetConfig.class,
        tableName = "worksheet_config"
)
public class WorksheetConfigDatabaseLink extends DatabaseLink {
    public WorksheetConfigDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("name", method("getName"), method("name", String.class)); // 2
    }
}
