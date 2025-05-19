package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {
    public Button doctors;
    public Button patients;
    public Button appointments;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctors.setOnAction(actionEvent -> onDoctors());
        patients.setOnAction(actionEvent -> onPatients());
        appointments.setOnAction(actionEvent -> onAppointments());
    }

    private void onDoctors() {
        Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.DOCTORS);
    }

    private void onPatients() {
        Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.PATIENTS);
    }

    private void onAppointments() {
        Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.APPOINTMENTS);
    }
}
