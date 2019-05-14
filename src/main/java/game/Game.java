package game;

import game.actors.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Game {
    private static Logger log = Logger.getLogger(Game.class);

    public UUID uuid;
    private HashMap<UUID, Player> players = new HashMap<>();
    private HashMap<UUID, Admin> admins = new HashMap<>();
    private HashMap<UUID, Question> questions = new HashMap<>();

    private HashMap<Player, Score> scores = new HashMap<>();

    private List<Question> questionOrder = new ArrayList<>();

    private HashMap<Player, Answer> currentAnswers = new HashMap<>();

    private Question currentQuestion;
    private Integer questionCount = 0;

    public Game() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player createPlayer() {
        Player player = new Player();
        players.put(player.getUuid(), player);
        scores.put(player, new Score(this, player));
        return player;
    }

    public Admin createAdmin() {
        Admin admin = new Admin();
        admins.put(admin.getUuid(), admin);
        return admin;
    }

    public void addQuestion(Question question) {
        questions.put(question.getUuid(), question);
        questionOrder.add(question);
    }

    public Player findPlayer(String uuidStr) {
        return players.get(UUID.fromString(uuidStr));
    }

    public Question findQuestion(String uuidStr) {
        return questions.get(UUID.fromString(uuidStr));
    }

    public Question getNextQuestion() {
        Question nextQuestion;
        if (questionOrder.size() > questionCount) {
            nextQuestion = questionOrder.get(questionCount);
        } else {
            nextQuestion = questionOrder.get(questionOrder.size() - 1);
        }
        questionCount++;
        this.currentQuestion = nextQuestion;
        return nextQuestion;
    }

    public Admin findAdmin(String uuidStr) {
        return admins.get(UUID.fromString(uuidStr));
    }

    public List<Player> getPlayers() {
        return new ArrayList(players.values());
    }

    public Score getScore(Player player) {
        return scores.get(player);
    }

    public List<Question> getQuestions() {
        return new ArrayList(questions.values());
    }

    public void updateAnswer(Answer answer) {
        currentAnswers.put(answer.getPlayer(), answer);
    }

    public void markAnswers() {
        for (Player player : currentAnswers.keySet()) {
            Answer answer = currentAnswers.get(player);

            if (currentQuestion.getCorrectOption().getUuid().toString().equals(answer.getAnswerValue())) {
                scores.get(player).givePoint();
                log.info("Answer was CORRECT for " + player.getName() + " answered " + answer.getAnswerValue() + " correct was " + currentQuestion.getCorrectOption().getUuid());
            } else {
                log.info("Answer was WRONG for " + player.getName() + " answered " + answer.getAnswerValue() + " correct was " + currentQuestion.getCorrectOption().getUuid());
            }
        }

        currentAnswers.clear();
    }
}
