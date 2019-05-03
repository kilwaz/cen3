package game.actors;

import java.util.UUID;

public class Answer {
    private UUID uuid;
    private String answerValue;
    private Player player;

    public Answer() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Answer answerValue(String answerValue) {
        this.answerValue = answerValue;
        return this;
    }

    public Answer player(Player player) {
        this.player = player;
        return this;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public Player getPlayer() {
        return player;
    }
}
