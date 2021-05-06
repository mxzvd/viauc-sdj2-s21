package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

// Typical ViewHandler class.
public class ViewHandler {
    
    private Stage primaryStage;
    private Scene currentScene;
    private ViewModelFactory viewModelFactory;
    private OverviewViewController overviewViewController;
    private LogViewController logViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        currentScene = new Scene(new Region());
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("overview");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
            case "overview" : root = loadOverviewView("OverviewView.fxml"); break;
            case "log" : root = loadLogView("LogView.fxml");
        }
        currentScene.setRoot(root);
        primaryStage.setTitle(root.getUserData() != null ? (String) root.getUserData() : "");
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    private Region loadOverviewView(String fxmlFile) {
        if (overviewViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                overviewViewController = loader.getController();
                overviewViewController.init(this, viewModelFactory.getOverviewViewModel(), root);
            } catch (Exception e) {
                // Do nothing
            }
        } else {
            overviewViewController.reset();
        }
        return overviewViewController.getRoot();
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
                // Do nothing
            }
        } else {
            logViewController.reset();
        }
        return logViewController.getRoot();
    }
}
