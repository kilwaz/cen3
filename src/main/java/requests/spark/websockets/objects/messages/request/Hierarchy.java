package requests.spark.websockets.objects.messages.request;

import clarity.definition.HierarchyNode;
import data.model.dao.HierarchyNodeDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.HierarchyData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("Hierarchy")
@WebSocketDataClass(HierarchyData.class)
public class Hierarchy extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        HierarchyData hierarchyData = (HierarchyData) this.getWebSocketData();
        JSONArray hierarchyJSON = new JSONArray();

        HierarchyNodeDAO hierarchyNodeDAO = new HierarchyNodeDAO();
        HierarchyNode hierarchyNode = hierarchyNodeDAO.getNodeByReference("ARUP");
        hierarchyNode = hierarchyNodeDAO.populateChildren(hierarchyNode);

        hierarchyJSON.put(hierarchyNode.getAsHierarchyListItem().prepareForJSON());

        hierarchyData.setHierarchyItems(hierarchyJSON);
    }
}
