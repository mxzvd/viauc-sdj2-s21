package viewmodel;

public class ViewState {

    private String number;
    private boolean remove;

    public ViewState() {
        removeNumber();
        remove = false;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void removeNumber() {
        setNumber("");
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
}
