package requests.spark.websockets.objects;

public enum PushMessage {
    // ***** DEFINED PUSH MESSAGES *****
    ECHO_PUSH("EchoPush"),
    HEART_BEAT("HeartBeat");

    private String type;

    PushMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
