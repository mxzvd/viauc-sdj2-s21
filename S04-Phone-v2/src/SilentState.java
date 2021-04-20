public class SilentState extends AlertState {

    @Override public void click(Phone phone) {
        if (phone.getVolume() == 0) for (int i = 0; i < 5; i++) phone.volumeUp();
        phone.setState(new SoundState());
    }

    @Override public String alert() {
        return "Silent State:";
    }

    @Override public void volumeUp(Phone phone) {
        phone.setState(new SoundState());
    }
}
