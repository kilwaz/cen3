package game.actors;

import java.util.UUID;

public class QuestionOption {
    private UUID uuid;
    private String answerValue;
    private Integer countedAnswers = 0;

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

    public Integer getCountedAnswers() {
        return countedAnswers;
    }

    public QuestionOption incrementAnswer(){
        countedAnswers++;
        return this;
    }

    public void setCountedAnswers(Integer countedAnswers) {
        this.countedAnswers = countedAnswers;
    }
}
