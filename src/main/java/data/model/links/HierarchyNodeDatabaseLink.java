package data.model.links;

import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.HierarchyNode.class,
        tableName = "hierarchy_nodes"
)
public class HierarchyNodeDatabaseLink extends DatabaseLink {
    public HierarchyNodeDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("node_reference", method("getNodeReference"), method("nodeReference", String.class)); // 2
        link("node_name", method("getNodeName"), method("nodeName", String.class)); // 3
        link("parent_reference", method("getParentReference"), method("parentReference", String.class)); // 4
        link("path_from_top", method("getPathFromTop"), method("pathFromTop", String.class)); // 5
    }
}
