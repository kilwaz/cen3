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
    NODE_LIST(),
    NODE_VALUE(),
    NODE_TYPE(),
    NODE_PRECEDENCE(),

    FORMULA_NODE(),
    FORMULA_STR_EXPRESSION(),

    RECORD_UUID(),
    RECORD_ENTRIES(),

    ENTRY_UUID(),
    ENTRY_NAME(),
    ENTRY_VALUE(),
    ENTRY_RECORD_UUID(),

    DEFINITION_UUID(),
    DEFINITION_NAME(),
    DEFINITION_EXPRESSION(),

    MENU_ITEM_ROUTELINK(),
    MENU_ITEM_TITLE(),
    MENU_ITEM_ICON(),

    WSData() {
    }
}
