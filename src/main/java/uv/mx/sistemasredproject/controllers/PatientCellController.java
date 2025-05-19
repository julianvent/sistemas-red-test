package uv.mx.sistemasredproject.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.server.models.Paciente;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientCellController implements Initializable {
    private final Paciente patient;
    public Label idLabel;
    public Label nameLabel;
    public Label curpLabel;
    public Label phoneLabel;
    public Label emailLabel;

    public PatientCellController(Paciente patient) {
        this.patient = patient;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLabel.textProperty().bind(patient.pacienteIdProperty().asString());
        nameLabel.textProperty().bind(patient.nombreProperty());
        curpLabel.textProperty().bind(patient.curpProperty());
        phoneLabel.textProperty().bind(patient.telefonoProperty());
        emailLabel.textProperty().bind(patient.correoElectronicoProperty());
    }
}
