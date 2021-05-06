import external.IndoorThermometer;
import external.OutdoorThermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import mediator.TemperatureModel;
import mediator.TemperatureModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {

    private Thread[] thermometers;

    public void start(Stage primaryStage) {
        TemperatureModel model = new TemperatureModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);

        thermometers = new Thread[] {
                new Thread(new OutdoorThermometer(model, "outside", -50, 50), "outside"),
                new Thread(new IndoorThermometer(model, "indoorT1", "outside", 1), "indoorT1"),
                new Thread(new IndoorThermometer(model, "indoorT2", "outside", 7), "indoorT2")
        };

        for (Thread thermometer : thermometers) thermometer.start();
    }

    @Override public void stop() {
        for (Thread thermometer : thermometers) thermometer.stop();
    }
}
