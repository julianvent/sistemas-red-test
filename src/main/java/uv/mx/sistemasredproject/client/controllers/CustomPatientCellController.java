package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.model.Paciente;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomPatientCellController implements Initializable {
    private final Paciente patient;
    public Label nameLabel;
    public Label phoneLabel;

    public CustomPatientCellController(Paciente patient) {
        this.patient = patient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.textProperty().bind(patient.nombreProperty());
        phoneLabel.textProperty().bind(patient.telefonoProperty());
    }
}
