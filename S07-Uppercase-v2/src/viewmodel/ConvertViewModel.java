package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ConvertViewModel {

    private StringProperty request;
    private StringProperty reply;
    private StringProperty error;
    private Model model;

    public ConvertViewModel(Model model) {
        request = new SimpleStringProperty();
        reply = new SimpleStringProperty();
        error = new SimpleStringProperty();
        this.model = model;
    }

    public StringProperty requestProperty() {
        return request;
    }

    public StringProperty replyProperty() {
        return reply;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void convert() {
        try {
            reply.set(model.convert(request.get()));
            error.set(null);
        } catch (Exception e) {
            error.set(e.getMessage());
        }
    }

    public void clear() {
        request.set("");
        reply.set("");
        error.set("");
    }
}
