package requests.spark.websockets.objects.messages.mapping;

import requests.spark.websockets.objects.messages.incoming.AdminGame;

import java.util.UUID;

@WebSocketLinkClass(linkClass = AdminGame.class)
public class AdminGameLink extends WebSocketAPI {
    public AdminGameLink() {
        super();

        link("uuid", method("getUuidString"), method("setUuid", UUID.class));
        link("value", method("getValue"), method("setValue", String.class));
    }
}
