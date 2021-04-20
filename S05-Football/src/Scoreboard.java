import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Scoreboard implements PropertyChangeListener {

    private FootballGame game;

    public Scoreboard(FootballGame game) {
        this.game = game;
        showScore(game.getScore());
        game.addListener(game.getHomeTeam(), this);
        game.addListener(game.getAwayTeam(), this);
    }

    public void showScore(String score) {
        System.out.println("SCOREBOARD: " + score);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        showScore(game.getScore());
    }
}
