package uv.mx.sistemasredproject.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    // utility
    private void createStage(FXMLLoader loader) {
        Scene scene;

        try {
            scene = new Scene(loader.load(), 424,424);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("LosqueSaben System");
        stage.show();
    }

    // actual views
    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hello-view.fxml"));
        createStage(loader);
    }
}
