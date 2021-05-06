package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;
import java.io.IOException;

public class ViewHandler extends ViewCreator {

    private ViewModelFactory viewModelFactory;
    private Scene currentScene;
    private Stage primaryStage;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        openView(View.AUTHORIZATION);
    }

    public void openView(View view) throws IOException {
        Region root = getViewController(view).getRoot();
        currentScene.setRoot(root);

        String title;
        primaryStage.setTitle((title = (String) root.getUserData()) != null ? title : "");
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth() + 25);
        primaryStage.setHeight(root.getPrefHeight() + 43);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    protected void initViewController(ViewController viewController, Region root) {
        viewController.init(this, viewModelFactory, root);
    }
}
