package requests.spark.websockets.objects.messages.mapping;

public enum WSData {
    // ***** DEFINED Web Socket Data *****
    PLAYER_UUID(),
    PLAYER_ID(),
    PLAYER_NAME();

    private String description;

    WSData() {
        this.description = "Hey";
    }
}
