package data.model.links;

import clarity.Record;
import clarity.definition.HierarchyTree;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.Hierarchy.class,
        tableName = "hierarchy"
)
public class HierarchyDatabaseLink extends DatabaseLink {
    public HierarchyDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("node_reference", method("getNodeReference"), method("nodeReference", String.class)); // 2
        link("parent_reference", method("getParentReference"), method("parentReference", String.class)); // 3
        link("tree_uuid", method("getHierarchyTreeUUID"), method("hierarchyTree", HierarchyTree.class)); // 4
        link("employee_uuid", method("getEmployeeUUID"), method("employee", Record.class)); // 5
    }
}
