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
    private LogViewController logViewController;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        currentScene = new Scene(new Region());
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("convert");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
            case "convert" : root = loadConverterView("ConvertView.fxml"); break;
            case "log" : root = loadLogView("LogView.fxml");
        }
        currentScene.setRoot(root);
        primaryStage.setTitle(root.getUserData() != null ? (String) root.getUserData() : "");
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    private Region loadConverterView(String fxmlFile) {
        if (convertViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                convertViewController = loader.getController();
                convertViewController.init(this, viewModelFactory.getConvertViewModel(), root);
            } catch (Exception e) {
                // Do nothing.
            }
        } else {
            convertViewController.reset();
        }
        return convertViewController.getRoot();
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
