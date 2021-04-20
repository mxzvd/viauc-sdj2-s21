import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import temperature.mediator.TemperatureModel;
import temperature.mediator.TemperatureModelManager;
import temperature.view.ViewHandler;

public class MyApplication extends Application {

    private Thermometer t1;
    private Thermometer t2;

    public void start(Stage primaryStage) {

        // Model
        TemperatureModel model = new TemperatureModelManager();

        // View
        ViewHandler view = new ViewHandler(model);
        view.start(primaryStage);

        t1 = new Thermometer(model, "t1", 15, 1);
        t2 = new Thermometer(model, "t2", 20, 3);

        Thread thread1 = new Thread(t1, "t1");
        Thread thread2 = new Thread(t2, "t2");

        thread1.start();
        thread2.start();
    }

    @Override
    public void stop() {
        t1.stop();
        t2.stop();
    }
}
