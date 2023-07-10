package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.RecalculateHierarchyData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import utils.HierarchyUtils;

@MessageType("RecalculateHierarchy")
@WebSocketDataClass(RecalculateHierarchyData.class)
public class RecalculateHierarchy extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        RecalculateHierarchyData recalculateHierarchyData = (RecalculateHierarchyData) this.getWebSocketData();

        HierarchyUtils.recalculate();
    }
}
