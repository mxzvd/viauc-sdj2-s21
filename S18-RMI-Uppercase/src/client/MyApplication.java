package client;

import javafx.application.Application;
import javafx.stage.Stage;
import client.model.Model;
import client.model.ModelManager;
import client.view.ViewHandler;
import client.viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application {

    private Model model;

    public void start(Stage primaryStage) {
        model = null;
        try {
            model = new ModelManager();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }

    @Override public void stop() {
        ((ModelManager) model).stop();
    }
}
