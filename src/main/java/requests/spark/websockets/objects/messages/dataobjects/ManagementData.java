package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class ManagementData extends WebSocketData {
    // OUTGOING
    @WSDataOutgoing
    private Double totalMemory = null;

    @WSDataOutgoing
    private Double freeMemory = null;

    @WSDataOutgoing
    private Double maxMemory = null;

    public Double getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(Double totalMemory) {
        this.totalMemory = totalMemory;
    }

    public Double getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(Double freeMemory) {
        this.freeMemory = freeMemory;
    }

    public Double getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Double maxMemory) {
        this.maxMemory = maxMemory;
    }
}
