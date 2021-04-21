package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConvertViewModel implements PropertyChangeListener {

    private Model model;
    private StringProperty request;
    private StringProperty reply;
    private StringProperty error;
    private StringProperty messageProperty;

    public ConvertViewModel(Model model) {
        this.model = model;
        error = new SimpleStringProperty();
        request = new SimpleStringProperty();
        reply = new SimpleStringProperty();
        messageProperty = new SimpleStringProperty();
        model.addListener(this);
    }

    public void convert() {
        try {
            String replyTxt = request.get();
            if (replyTxt == null) throw new Exception("Input a string.");
            reply.set(model.convert(replyTxt));
            request.set(null);
            error.set(null);
            messageProperty.set(null);
        } catch (Exception e) {
            error.set(e.getMessage());
        }
    }

    public StringProperty errorProperty() {
        return error;
    }

    public StringProperty getMessageProperty() {
        return messageProperty;
    }

    public StringProperty requestProperty() {
        return request;
    }

    public StringProperty replyProperty() {
        return reply;
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> messageProperty.set((String) evt.getNewValue()));
    }
}
