package uv.mx.sistemasredproject.client.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.client.controllers.ClientController;
import uv.mx.sistemasredproject.client.controllers.CreateAppointmentController;
import uv.mx.sistemasredproject.client.controllers.CreateDoctorController;
import uv.mx.sistemasredproject.client.controllers.CreatePatientController;
import uv.mx.sistemasredproject.model.Appointment;
import uv.mx.sistemasredproject.model.Doctor;
import uv.mx.sistemasredproject.model.Patient;

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

    private VBox getCreateDoctorView(Doctor doctor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/create-doctor.fxml"));
            CreateDoctorController controller = new CreateDoctorController(doctor);
            loader.setController(controller);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCreateDoctorDialog(Doctor doctor) {
        Dialog<String> dialog = new Dialog<>();
        if (doctor != null) dialog.getDialogPane().setContent(getCreateDoctorView(doctor));
        else dialog.getDialogPane().setContent(getCreateDoctorView(null));
        dialog.show();
    }


    // patients
    public VBox getPatientsView() {
        try {
            return createLoader("patient-view.fxml").load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private VBox getCreatePatientView(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/create-patient.fxml"));
            CreatePatientController controller = new CreatePatientController(patient);
            loader.setController(controller);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCreatePatientDialog(Patient patient) {
        Dialog<String> dialog = new Dialog<>();
        if (patient != null) dialog.getDialogPane().setContent(getCreatePatientView(patient));
        else dialog.getDialogPane().setContent(getCreatePatientView(null));
        dialog.show();
    }


    // appointments
    public VBox getAppointmentsView() {
        try {
            return createLoader("appointment-view.fxml").load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private VBox getCreateAppointmentView(Appointment appointment) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/create-appointment.fxml"));
            CreateAppointmentController controller = new CreateAppointmentController(appointment);
            loader.setController(controller);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCreateAppointmentDialog(Appointment appointment) {
        Dialog<String> dialog = new Dialog<>();
        if (appointment != null) dialog.getDialogPane().setContent(getCreateAppointmentView(appointment));
        else dialog.getDialogPane().setContent(getCreateAppointmentView(null));
        dialog.show();
    }
}
