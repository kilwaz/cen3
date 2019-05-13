package game.actors;

import java.util.UUID;

public class QuestionOption {
    private UUID uuid;
    private String answerValue;

    public QuestionOption() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public QuestionOption answerValue(String answerValue) {
        this.answerValue = answerValue;
        return this;
    }

    public String getAnswerValue() {
        return answerValue;
    }
}
