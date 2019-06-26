package requests.spark.websockets.objects.messages.mapping;

import requests.spark.websockets.objects.messages.dataobjects.AdminGameData;
import requests.spark.websockets.objects.messages.incoming.AdminGame;

import java.util.UUID;

@WebSocketLinkClass(linkClass = AdminGameData.class)
public class AdminGameLink extends WebSocketAPI {
    public AdminGameLink() {
        super();

        link("localStorageUUID", method("getLocalStorageUUID"), method("setLocalStorageUUID", String.class));
    }
}
