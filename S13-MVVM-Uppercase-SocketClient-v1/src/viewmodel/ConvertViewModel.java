package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ConvertViewModel {

    private Model model;
    private StringProperty request;
    private StringProperty reply;
    private StringProperty error;

    public ConvertViewModel(Model model) {
        this.model = model;
        error = new SimpleStringProperty();
        request = new SimpleStringProperty();
        reply = new SimpleStringProperty();
    }

    public void convert() {
        try {
            String replyTxt = model.convert(request.get());
            reply.set(replyTxt);
            request.set(null);
            error.set(null);
        } catch (Exception e) {
            error.set(e.getMessage());
        }
    }

    public StringProperty errorProperty() {
        return error;
    }

    public StringProperty requestProperty() {
        return request;
    }

    public StringProperty replyProperty() {
        return reply;
    }
}
