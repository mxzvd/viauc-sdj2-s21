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

    public void start(Stage primaryStage) throws IOException {
        model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }

    @Override public void stop() {
        ((ModelManager) model).stop();
    }
}
