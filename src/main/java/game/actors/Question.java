package game.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {
    public UUID uuid;

    private List<QuestionOption> possibleOptions = new ArrayList<>();
    private QuestionOption correctOption;
    private String questionText = "";

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
