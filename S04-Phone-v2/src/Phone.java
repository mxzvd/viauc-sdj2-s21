public class Phone {

    private int volume;
    private static final int MIN_VOLUME = 0;
    private static final int MAX_VOLUME = 10;

    private AlertState state = new SilentState();

    public void clickSoundButton() {
        state.click(this);
    }

    public void setState(AlertState state) {
        this.state = state;
    }

    public void volumeUp() {
        if (volume < MAX_VOLUME) {
            volume++;
            state.volumeUp(this);
        }
    }

    public void volumeDown() {
        if (volume > MIN_VOLUME) {
            volume--;
            state.volumeDown(this);
        }
    }

    public int getVolume() {
        return volume;
    }

    public void receive(String message) {
        System.out.println(state.alert() + "\n" + message);
    }

}
