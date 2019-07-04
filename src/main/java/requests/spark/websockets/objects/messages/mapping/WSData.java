package requests.spark.websockets.objects.messages.mapping;

public enum WSData {
    // ***** DEFINED Web Socket Data *****
    PLAYER_UUID(),
    PLAYER_ID(),
    PLAYER_NAME(),

    SCORE_GAME(),
    SCORE_PLAYER(),
    SCORE_SCORE(),

    QUESTION_OPTION_UUID(),
    QUESTION_OPTION_ANSWER_VALUE(),
    QUESTION_OPTION_COUNTED_ANSWERS();

    WSData() {
    }
}
