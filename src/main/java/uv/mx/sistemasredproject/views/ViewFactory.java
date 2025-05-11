package uv.mx.sistemasredproject.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.controllers.ClientController;
import uv.mx.sistemasredproject.controllers.CreateDoctorController;
import uv.mx.sistemasredproject.server.models.Medico;

import java.io.IOException;

public class ViewFactory {
    private final ObjectProperty<SubmenuOptions> selectedMenuItem;

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

    public void closeStage(Stage stage) {
        stage.close();
    }

    // actual views
    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/client.fxml"));
        ClientController controller = new ClientController();
        loader.setController(controller);
        createStage(loader);
    }

    public VBox getRefresh() {
        try {
            return createLoader("refresh.fxml").load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* --------
    submenus
    --------- */
    // doctors
    public VBox getDoctorsView() {
        try {
            return createLoader("doctor-view.fxml").load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private VBox getCreateDoctorView(Medico medico) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/create-doctor.fxml"));
            CreateDoctorController controller = new CreateDoctorController(medico);
            loader.setController(controller);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showCreateDoctorView(Medico medico) {
        Dialog<String> dialog = new Dialog<>();
        if (medico != null) dialog.getDialogPane().setContent(getCreateDoctorView(medico));
        else dialog.getDialogPane().setContent(getCreateDoctorView(null));
        dialog.show();
    }
}
