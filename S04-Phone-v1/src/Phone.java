public class Phone {

    private AlertState state = new SilentState();

    public void clickSoundButton() {
        state.click(this);
    }

    public void setState(AlertState state) {
        this.state = state;
    }

    public void receive(String message) {
        System.out.println(state.alert() + "\n" + message);
    }
}
