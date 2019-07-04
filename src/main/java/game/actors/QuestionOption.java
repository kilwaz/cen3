package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

import java.util.UUID;

public class QuestionOption extends JSONWeb {
    @WSDataReference(WSData.QUESTION_OPTION_UUID)
    private UUID uuid;
    @WSDataReference(WSData.QUESTION_OPTION_ANSWER_VALUE)
    private String answerValue;
    @WSDataReference(WSData.QUESTION_OPTION_COUNTED_ANSWERS)
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

    public QuestionOption incrementAnswer() {
        countedAnswers++;
        return this;
    }

    public void setCountedAnswers(Integer countedAnswers) {
        this.countedAnswers = countedAnswers;
    }
}
