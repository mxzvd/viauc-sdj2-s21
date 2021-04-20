package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {

    private Stage primaryStage;
    private Scene currentScene;
    private ConvertViewController convertViewController;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        currentScene = new Scene(new Region());
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("Uppercase");
    }

    public void openView(String id) {
        Region root = loadConverterView("ConvertView.fxml");
        currentScene.setRoot(root);
        primaryStage.setTitle(root.getUserData() != null ? (String) root.getUserData() : "");
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    private Region loadConverterView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            convertViewController = loader.getController();
            convertViewController.init(this, viewModelFactory.getConvertViewModel(), root);
        } catch (Exception e) {
            // Do nothing.
        }
        return convertViewController.getRoot();
    }
}
