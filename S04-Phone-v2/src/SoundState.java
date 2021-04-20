public class SoundState extends AlertState {

    @Override public void click(Phone phone) {
        phone.setState(new VibrationState());
    }

    @Override public String alert() {
        return "Sound State:";
    }

    @Override public void volumeDown(Phone phone) {
        if (phone.getVolume() == 0) {
            phone.setState(new SilentState());
        }
    }
}
