package uv.mx.sistemasredproject.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.server.models.Medico;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorCellController implements Initializable {
    private final Medico doctor;
    public Label idLabel;
    public Label nameLabel;
    public Label degreeLabel;
    public Label cedulaLabel;
    public Label emailLabel;

    public DoctorCellController(Medico doctor) {
        this.doctor = doctor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLabel.textProperty().bind(doctor.medicoIdProperty().asString());
        nameLabel.textProperty().bind(doctor.nombreProperty());
        degreeLabel.textProperty().bind(doctor.especialidadProperty());
        cedulaLabel.textProperty().bind(doctor.cedulaProperty());
        emailLabel.textProperty().bind(doctor.correoElectronicoProperty());
    }
}
