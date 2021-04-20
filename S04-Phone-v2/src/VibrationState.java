public class VibrationState extends AlertState {

    @Override public void click(Phone phone) {
        phone.setState(new SilentState());
    }

    @Override public String alert() {
        return "Vibration State:";
    }

}
