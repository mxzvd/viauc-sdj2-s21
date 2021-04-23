package client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import client.viewmodel.ViewModelFactory;

public class ViewHandler {

    private Stage primaryStage;
    private Scene currentScene;
    private ViewModelFactory viewModelFactory;
    private ConvertViewController convertViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region());
        openView("convert");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
        case "convert":
            root = loadConvertView("ConvertView.fxml");
            break;
        }
        currentScene.setRoot(root);

        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    private Region loadConvertView(String fxmlFile) {
        if (convertViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                convertViewController = loader.getController();
                convertViewController.init(this, viewModelFactory.getConvertViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            convertViewController.reset();
        }
        return convertViewController.getRoot();
    }
}
