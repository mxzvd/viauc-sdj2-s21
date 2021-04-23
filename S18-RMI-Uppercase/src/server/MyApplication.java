package server;

import javafx.application.Application;
import javafx.stage.Stage;
import server.mediator.Server;
import server.model.Model;
import server.model.ModelManager;
import server.view.SimpleConsoleView;
import server.view.ViewHandler;
import server.viewmodel.ViewModelFactory;

public class MyApplication extends Application {
    public void start(Stage primaryStage) {
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        SimpleConsoleView consoleView = new SimpleConsoleView(model);
        try {
            Server server = new Server(model);
        } catch (Exception e) {
            System.err.println("Could not start the server");
            System.err.println(e.getMessage());
        }
        view.start(primaryStage);
    }
}
