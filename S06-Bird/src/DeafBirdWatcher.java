import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeafBirdWatcher implements PropertyChangeListener {

    private Bird bird;

    public DeafBirdWatcher(Bird bird) {
        this.bird = bird;
        bird.addListener("birdIsFlying", this);
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("DeafBirdWatcher: The bird is flying really beautifully.");
    }
}
