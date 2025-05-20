package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.model.Patient;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientCellController implements Initializable {
    private final Patient patient;
    public Label idLabel;
    public Label nameLabel;
    public Label curpLabel;
    public Label phoneLabel;
    public Label emailLabel;

    public PatientCellController(Patient patient) {
        this.patient = patient;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLabel.setText(String.valueOf(patient.getId()));
        nameLabel.setText(patient.getName());
        curpLabel.setText(patient.getCurp());
        phoneLabel.setText(patient.getPhone());
        emailLabel.setText(patient.getEmail());
    }
}
