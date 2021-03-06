package game;

//import error.Error;
//import game.actors.Question;
//import game.actors.QuestionOption;
//import log.AppLogger;
//import org.apache.log4j.Logger;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.UUID;

public class GameManager {
//    private static GameManager instance;
//    private static Logger log = AppLogger.logger();
//
//    private HashMap<UUID, Game> games = new HashMap<>();
//    private Game currentGame = null;
//
//    private GameManager() {
//        instance = this;
//    }
//
//    public static GameManager getInstance() {
//        if (instance == null) {
//            instance = new GameManager();
//        }
//        return instance;
//    }
//
//    public Game createNewGame() {
//        Game game = new Game();
//
//        File file = new File(getClass().getClassLoader().getResource("questions.txt").getFile());
//
//        if (file.exists()) {
//            try {
//                try (FileReader reader = new FileReader(file);
//                     BufferedReader br = new BufferedReader(reader)) {
//
//                    String line;
//                    while ((line = br.readLine()) != null) {
//
//                        String[] splitLine = line.split("\t");
//                        if (splitLine.length == 5) {
//                            QuestionOption answer1 = new QuestionOption().answerValue(splitLine[1]);
//                            QuestionOption answer2 = new QuestionOption().answerValue(splitLine[2]);
//                            QuestionOption answer3 = new QuestionOption().answerValue(splitLine[3]);
//                            QuestionOption answer4 = new QuestionOption().answerValue(splitLine[4]);
//
//                            answer1.isCorrectAnswer(true);
//
//                            Question question = new Question()
//                                    .questionText(splitLine[0])
//                                    .correctOption(answer1)
//                                    .wrongOption(answer2)
//                                    .wrongOption(answer3)
//                                    .wrongOption(answer4);
//
//                            game.addQuestion(question);
//                        }
//                    }
//                }
//            } catch (IOException ex) {
//                Error.DATA_LOAD_EXCEPTION.record().create(ex);
//            }
//        }
//
//        log.info("Created new game, ready to play - " + game.getQuestions().size() + " questions loaded");
//
//        games.put(game.getUuid(), game);
//        currentGame = game;
//        return game;
//    }
//
//    public Game getCurrentGame() {
//        return currentGame;
//    }
}
