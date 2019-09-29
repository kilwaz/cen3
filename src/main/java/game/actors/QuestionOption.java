package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

import java.util.UUID;

public class QuestionOption extends JSONWeb {
    @WSDataReference(WSData.QUESTION_OPTION_UUID)
    private UUID uuid;
    @WSDataReference(WSData.QUESTION_OPTION_ANSWER_VALUE)
    private String answerValue = "";
    @WSDataReference(WSData.QUESTION_OPTION_COUNTED_ANSWERS)
    private Integer countedAnswers = 0;
    @WSDataReference(WSData.QUESTION_OPTION_IS_CORRECT_ANSWER)
    private Boolean isCorrectAnswer = false;
    @WSDataReference(WSData.QUESTION_OPTION_OPTION_PERCENTAGE_OF_TOTAL_ANSWERS)
    private Double optionPercentageOfTotalAnswers = 0d;
    @WSDataReference(WSData.QUESTION_OPTION_ANSWER_PROGRESS_CLASS)
    private String answerProgressClass = "alert-light";

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

    public Boolean getIsCorrectAnswer() {
        return isCorrectAnswer;
    }

    public QuestionOption isCorrectAnswer(Boolean isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
        return this;
    }

    public void setCountedAnswers(Integer countedAnswers) {
        this.countedAnswers = countedAnswers;
    }

    public QuestionOption optionPercentageOfTotalAnswers(Double optionPercentageOfTotalAnswers) {
        this.optionPercentageOfTotalAnswers = optionPercentageOfTotalAnswers;
        return this;
    }

    public Double getOptionPercentageOfTotalAnswers() {
        return optionPercentageOfTotalAnswers;
    }

    public QuestionOption answerProgressClass(String answerProgressClass) {
        this.answerProgressClass = answerProgressClass;
        return this;
    }

    public String getAnswerProgressClass() {
        return answerProgressClass;
    }
}
