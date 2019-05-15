package game.actors;

import game.Game;
import org.apache.log4j.Logger;

public class Score {
    private static Logger log = Logger.getLogger(Score.class);

    private Game game;
    private Player player;
    private Integer score = 0;

    public Score(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getScore() {
        return score;
    }

    public Score givePoint() {
        score++;
        log.info("Score is now " + score + " for " + player.getUuid());
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
