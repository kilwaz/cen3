package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question extends JSONWeb {
    @WSDataReference(WSData.QUESTION_UUID)
    private UUID uuid;

    @WSDataReference(WSData.QUESTION_POSSIBLE_OPTIONS)
    @WSDataTypeScriptClass(QuestionOption.class)
    private List<QuestionOption> possibleOptions = new ArrayList<>();

    @WSDataReference(WSData.QUESTION_CORRECT_OPTION)
    @WSDataTypeScriptClass(QuestionOption.class)
    private QuestionOption correctOption;

    @WSDataReference(WSData.QUESTION_QUESTION_TEXT)
    private String questionText = "";

    @WSDataReference(WSData.QUESTION_TOTAL_ANSWERS)
    private Integer totalAnswers = 0;

    public Question() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Question correctOption(QuestionOption questionOption) {
        possibleOptions.add(questionOption);
        this.correctOption = questionOption;
        return this;
    }

    public List<QuestionOption> getPossibleOptions() {
        return possibleOptions;
    }

    public QuestionOption getCorrectOption() {
        return correctOption;
    }

    public Question wrongOption(QuestionOption questionOption) {
        possibleOptions.add(questionOption);
        return this;
    }

    public Question questionText(String questionText) {
        this.questionText = questionText;
        return this;
    }

    public String getQuestionText() {
        return questionText;
    }

    public QuestionOption findQuestionOption(String uuid) {
        for (QuestionOption questionOption : possibleOptions) {
            if (questionOption.getUuid().toString().equals(uuid)) {
                return questionOption;
            }
        }
        return null;
    }
}
