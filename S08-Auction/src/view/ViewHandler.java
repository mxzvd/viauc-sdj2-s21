package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {

    private Scene currentScene;
    private Stage primaryStage;
    private ViewModelFactory viewModelFactory;
    private AuctionItemViewController auctionItemViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("item");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
        case "item":
            root = loadAuctionItemView("AuctionItemView.fxml");
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

    public void closeView() {
        primaryStage.close();
    }

    private Region loadAuctionItemView(String fxmlFile) {
        if (auctionItemViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                auctionItemViewController = loader.getController();
                auctionItemViewController.init(this, viewModelFactory.getAuctionItemViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            auctionItemViewController.reset();
        }
        return auctionItemViewController.getRoot();
    }
}