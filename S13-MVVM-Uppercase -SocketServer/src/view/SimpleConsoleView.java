package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Model;

public class SimpleConsoleView implements PropertyChangeListener {

    public SimpleConsoleView(Model model) {
        model.addListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("-->" + evt.getNewValue());
    }
}
