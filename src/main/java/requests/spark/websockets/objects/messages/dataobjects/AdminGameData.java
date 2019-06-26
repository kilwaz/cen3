package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.incoming.AdminGame;

@WebSocketDataClass(AdminGame.class)
public class AdminGameData extends WebSocketData {
    private String localStorageUUID = "";

    public String getLocalStorageUUID() {
        return localStorageUUID;
    }

    public void setLocalStorageUUID(String localStorageUUID) {
        this.localStorageUUID = localStorageUUID;
    }
}
