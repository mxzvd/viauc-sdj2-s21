package client.view;

public enum View {

    AUTHORIZATION("AuthorizationView.fxml"),
    APPLICATION("ApplicationView.fxml");

    private String fxmlFile;
    private ViewController viewController;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }
}
