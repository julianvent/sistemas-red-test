package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.model.Doctor;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomDoctorCellController implements Initializable {
    private final Doctor doctor;
    public Label nameLabel;
    public Label degreeLabel;

    public CustomDoctorCellController(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(doctor.getName());
        degreeLabel.setText(doctor.getDegree());
    }
}
