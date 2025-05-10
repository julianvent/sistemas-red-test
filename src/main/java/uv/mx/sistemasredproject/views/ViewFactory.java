package uv.mx.sistemasredproject.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private final ObjectProperty<SubmenuOptions> selectedMenuItem;
    private VBox doctorsView;

    public ViewFactory() {
        selectedMenuItem = new SimpleObjectProperty<>(SubmenuOptions.DOCTORS);
    }

    public ObjectProperty<SubmenuOptions> selectedMenuItemProperty() {
        return selectedMenuItem;
    }


    // utility
    private void createStage(FXMLLoader loader) {
        Scene scene;

        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("LosqueSaben System");
        stage.show();
    }

    private FXMLLoader createLoader(String source) {
        return new FXMLLoader(getClass().getResource("/fxml/" + source));
    }

    // actual views
    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/client.fxml"));
        createStage(loader);
    }

    /* --------
    submenus
    --------- */
    // doctors
    public VBox getDoctorsView() {
        if (doctorsView == null) {
            try {
                doctorsView = createLoader("doctors-view.fxml").load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return doctorsView;
    }

    public void showAddDoctorView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/create-doctor.fxml"));
        createStage(loader);
    }

    //
}
