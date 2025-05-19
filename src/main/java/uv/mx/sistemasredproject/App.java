package uv.mx.sistemasredproject;

import javafx.application.Application;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.client.model.Model;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showClientWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}