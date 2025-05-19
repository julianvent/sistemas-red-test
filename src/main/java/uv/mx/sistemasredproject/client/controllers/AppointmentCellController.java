package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.model.Cita;
import uv.mx.sistemasredproject.model.Medico;
import uv.mx.sistemasredproject.model.Paciente;
import uv.mx.sistemasredproject.server.models.ServerModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentCellController implements Initializable {
    private final Cita appointment;
    public Label idLabel;
    public Label dateLabel;
    public Label timeLabel;
    public Label motivoLabel;
    public Label doctorNameLabel;
    public Label doctorDegreeLabel;
    public Label patientNameLabel;
    public Label patientPhoneLabel;

    public AppointmentCellController(Cita appointment) {
        this.appointment = appointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Medico doctor = ServerModel.getInstance().getDatabaseDriver().getMedico(appointment.getMedicoId());
        Paciente patient = ServerModel.getInstance().getDatabaseDriver().getPaciente(appointment.getPacienteId());

        idLabel.textProperty().bind(appointment.citaIdProperty().asString());
        dateLabel.textProperty().bind(appointment.fechaHoraProperty());
        motivoLabel.textProperty().bind(appointment.motivoProperty());
        doctorNameLabel.textProperty().bind(doctor.nombreProperty());
        doctorDegreeLabel.textProperty().bind(doctor.especialidadProperty());
        patientNameLabel.textProperty().bind(patient.nombreProperty());
        patientPhoneLabel.textProperty().bind(patient.telefonoProperty());
    }
}
