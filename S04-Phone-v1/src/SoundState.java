public class SoundState implements AlertState {

    @Override
    public void click(Phone phone) {
        phone.setState(new VibrationState());
    }

    @Override
    public String alert() {
        return "Sound State:";
    }
}
