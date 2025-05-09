package uv.mx.sistemasredproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.views.ViewFactory;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}