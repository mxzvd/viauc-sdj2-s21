package server.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import server.viewmodel.ViewModelFactory;

public class ViewHandler {

    private Stage primaryStage;
    private Scene currentScene;
    private LogViewController logViewController;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        currentScene = new Scene(new Region());
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("log");
    }

    public void openView(String id) {
        Region root = null;
        root = loadLogView("LogView.fxml");
        currentScene.setRoot(root);
        primaryStage.setTitle(root.getUserData() != null ? (String) root.getUserData() : "");
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    private Region loadLogView(String fxmlFile) {
        if (logViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                logViewController = loader.getController();
                logViewController.init(this, viewModelFactory.getLogViewModel(), root);
            } catch (Exception e) {
                // Do nothing.
            }
        }
        return logViewController.getRoot();
    }
}
