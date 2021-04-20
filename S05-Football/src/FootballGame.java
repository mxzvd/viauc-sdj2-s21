import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FootballGame implements NamedPropertyChangeSubject {

    private String homeTeam;
    private String awayTeam;
    private int homeTeamGoal;
    private int awayTeamGoal;
    private PropertyChangeSupport property;

    public FootballGame(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoal = 0;
        this.awayTeamGoal = 0;
        property = new PropertyChangeSupport(this);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void scoreGoal(String team) {
        if (team.equals(homeTeam)) {
            property.firePropertyChange(homeTeam, homeTeamGoal++, homeTeamGoal);
        } else if (team.equals(awayTeam)) {
            property.firePropertyChange(awayTeam, awayTeamGoal++, awayTeamGoal);
        }
    }

    public String getScore() {
        return homeTeamGoal + " - " + awayTeamGoal;
    }

    public String endGame() {
        return getScore();
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        property.removePropertyChangeListener(propertyName, listener);
    }
}
