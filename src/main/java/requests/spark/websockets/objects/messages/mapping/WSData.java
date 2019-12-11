package requests.spark.websockets.objects.messages.mapping;

public enum WSData {
    // ***** DEFINED Web Socket Data *****
    // COULD THIS BE AUTO GENERATED?
    PLAYER_UUID(),
    PLAYER_ID(),
    PLAYER_NAME(),
    PLAYER_SCORE(),
    PLAYER_PLAYER_STATUS(),
    PLAYER_LATEST_ANSWER(),

    NODE_LEFT(),
    NODE_RIGHT(),
    NODE_VALUE(),
    NODE_PRECEDENCE(),

    FORMULA_NODE(),
    FORMULA_STR_EXPRESSION();

    WSData() {
    }
}
