package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.model.Patient;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomPatientCellController implements Initializable {
    private final Patient patient;
    public Label nameLabel;
    public Label phoneLabel;

    public CustomPatientCellController(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(patient.getName());
        phoneLabel.setText(patient.getPhone());
    }
}
