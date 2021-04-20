import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BlindBirdWatcher implements PropertyChangeListener {

    private Bird bird;

    public BlindBirdWatcher(Bird bird) {
        this.bird = bird;
        bird.addListener("birdIsSinging", this);
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("BlindBirdWatcher: The bird is singing really beautifully.");
    }
}
