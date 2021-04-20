import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

public class BirdWatcher implements PropertyChangeListener {

    private static final String[] phrases = {"Ooh", "How nice", "Would you look at that"};
    private Random random;
    private Bird bird;

    public BirdWatcher(Bird bird) {
        random = new Random();
        this.bird = bird;
        bird.addListener("birdIsFlying", this);
        bird.addListener("birdIsSinging", this);
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("BirdWatcher: " + phrases[random.nextInt(3)]);
    }
}
