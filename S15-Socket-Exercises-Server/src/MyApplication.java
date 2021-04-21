import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ExercisesServer;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;
import java.io.IOException;

public class MyApplication extends Application {

    private ExercisesServer server;

    public void start(Stage primaryStage) {
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        try {
            server = new ExercisesServer(model);
            new Thread(server).start();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        viewHandler.start(primaryStage);
    }

    @Override
    public void stop() {
        server.close();
    }
}
