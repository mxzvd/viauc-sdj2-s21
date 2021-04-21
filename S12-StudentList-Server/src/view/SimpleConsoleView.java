package view;

import model.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SimpleConsoleView implements PropertyChangeListener {

    public SimpleConsoleView(Model model) {
        model.addListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName() + ": " + evt.getNewValue());
    }
}
