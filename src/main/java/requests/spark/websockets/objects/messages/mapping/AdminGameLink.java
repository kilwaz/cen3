package requests.spark.websockets.objects.messages.mapping;

import org.json.JSONArray;
import requests.spark.websockets.objects.messages.dataobjects.AdminGameData;

import java.util.UUID;

@WebSocketLinkClass(linkClass = AdminGameData.class)
public class AdminGameLink extends WebSocketAPI {
    public AdminGameLink() {
        super();

        inLink("localStorageUUID", method("getLocalStorageUUID"), method("setLocalStorageUUID", String.class));

        outLink("adminUUID", method("getAdminUUID"), method("setAdminUUID", UUID.class));
        outLink("allPlayers", method("getAllPlayers"), method("setAllPlayers", JSONArray.class));
    }
}
