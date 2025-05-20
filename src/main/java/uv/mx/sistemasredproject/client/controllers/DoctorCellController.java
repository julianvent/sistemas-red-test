package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.model.Doctor;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorCellController implements Initializable {
    private final Doctor doctor;
    public Label idLabel;
    public Label nameLabel;
    public Label degreeLabel;
    public Label cedulaLabel;
    public Label emailLabel;

    public DoctorCellController(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLabel.setText(doctor.getId() + "");
        nameLabel.setText(doctor.getName());
        degreeLabel.setText(doctor.getDegree());
        cedulaLabel.setText(doctor.getCedula());
        emailLabel.setText(doctor.getEmail());
    }
}
