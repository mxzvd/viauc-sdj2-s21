import javafx.application.Application;
import javafx.stage.Stage;
import model.TemperatureModel;
import model.TemperatureModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {
    public void start(Stage primaryStage) {
        TemperatureModel model = new TemperatureModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }
}
