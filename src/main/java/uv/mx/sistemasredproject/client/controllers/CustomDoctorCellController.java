package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.model.Medico;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomDoctorCellController implements Initializable {
    private final Medico doctor;
    public Label nameLabel;
    public Label degreeLabel;

    public CustomDoctorCellController(Medico doctor) {
        this.doctor = doctor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.textProperty().bind(doctor.nombreProperty());
        degreeLabel.textProperty().bind(doctor.especialidadProperty());
    }
}
