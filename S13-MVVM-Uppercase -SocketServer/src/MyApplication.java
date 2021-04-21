import javafx.application.Application;
import javafx.stage.Stage;
import mediator.UppercaseConnector;
import model.Model;
import model.ModelManager;
import view.SimpleConsoleView;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {
    public void start(Stage primaryStage) {
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        SimpleConsoleView consoleView = new SimpleConsoleView(model);
        Thread connector = new Thread(new UppercaseConnector(model));
        connector.setDaemon(true);
        connector.start();
        view.start(primaryStage);
    }
}
