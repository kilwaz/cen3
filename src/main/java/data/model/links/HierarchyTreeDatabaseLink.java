package data.model.links;

import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.HierarchyTree.class,
        tableName = "hierarchy_trees"
)
public class HierarchyTreeDatabaseLink extends DatabaseLink {
    public HierarchyTreeDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("name", method("getName"), method("name", String.class)); // 2
    }
}
