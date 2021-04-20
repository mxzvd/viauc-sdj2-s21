import javafx.application.Application;
import javafx.stage.Stage;
import model.AuctionHouseSimulator;
import model.AuctionModel;
import model.AuctionModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {
    public void start(Stage primaryStage) {
        AuctionModel model = new AuctionModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }
}
