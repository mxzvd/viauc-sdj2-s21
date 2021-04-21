package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {

    private Stage primaryStage;
    private Scene currentScene;
    private ListExercisesViewController listExercisesViewController;
    private ManageExerciseViewController manageExerciseViewController;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        currentScene = new Scene(new Region());
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("list");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
            case "list" : root = loadListView("ListExercisesView.fxml"); break;
            case "manage" : root = loadManageView("ManageExerciseView.fxml");
        }
        currentScene.setRoot(root);
        primaryStage.setTitle(root.getUserData() != null ? (String) root.getUserData() : "");
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    private Region loadListView(String fxmlFile) {
        if (listExercisesViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                listExercisesViewController = loader.getController();
                listExercisesViewController.init(this, viewModelFactory.getListExerciseViewModel(), root);
            } catch (Exception e) {
                // Do nothing
            }
        } else {
            listExercisesViewController.reset();
        }
        return listExercisesViewController.getRoot();
    }

    private Region loadManageView(String fxmlFile) {
        if (manageExerciseViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                manageExerciseViewController = loader.getController();
                manageExerciseViewController.init(this, viewModelFactory.getManageExerciseViewModel(), root);
            } catch (Exception e) {
                // Do nothing.
            }
        } else {
            manageExerciseViewController.reset();
        }
        return manageExerciseViewController.getRoot();
    }
}
