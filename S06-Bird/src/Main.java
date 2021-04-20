public class Main {
    public static void main(String[] args) {

        Bird bird = new Bird();
        BirdWatcher birdWatcher = new BirdWatcher(bird);
        BlindBirdWatcher blindBirdWatcher = new BlindBirdWatcher(bird);
        DeafBirdWatcher deafBirdWatcher = new DeafBirdWatcher(bird);

        bird.fly();
        bird.fly();
        bird.sing();
        bird.fly();
        bird.sing();
    }
}
