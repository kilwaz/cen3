package requests.spark.websockets.objects;

public enum PushMessage {
    // ***** DEFINED PUSH MESSAGES *****
    ANSWER_UPDATE("AnswerUpdate"),
    CLEAR_GAME_SCREEN("ClearGameScreen"),
    DISPLAY_GAME_MESSAGE("DisplayGameMessage"),
    HEART_BEAT("HeartBeat"),
    NEW_PLAYER_JOINED("NewPlayerJoined"),
    NEXT_QUESTION("NextQuestion"),
    PLAYER_NAME_UPDATE("PlayerNameUpdate"),
    QUESTION_RESULTS("QuestionResults"),
    START_COUNT_DOWN("StartCountDown"),
    UPDATE_SCORE("UpdateScore");

    private String type;

    PushMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
