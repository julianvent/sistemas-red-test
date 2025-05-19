package uv.mx.sistemasredproject.client.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.model.Cita;
import uv.mx.sistemasredproject.model.Medico;
import uv.mx.sistemasredproject.model.Paciente;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AppointmentCellController implements Initializable {
    private final Cita appointment;
    public Label idLabel;
    public Label dateLabel;
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
        Medico doctor = null;
        try {
            doctor = Model.getInstance().getMedicoService().getMedico(appointment.getMedicoId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        Paciente patient = null;
        try {
            patient = Model.getInstance().getMedicoService().getPaciente(appointment.getPacienteId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        StringProperty dateProperty =
                new SimpleStringProperty(appointment.getFechaHora().replace(":00.0", ""));

        idLabel.textProperty().bind(appointment.citaIdProperty().asString());
        dateLabel.textProperty().bind(dateProperty);
        motivoLabel.textProperty().bind(appointment.motivoProperty());
        doctorNameLabel.textProperty().bind(doctor.nombreProperty());
        doctorDegreeLabel.textProperty().bind(doctor.especialidadProperty());
        patientNameLabel.textProperty().bind(patient.nombreProperty());
        patientPhoneLabel.textProperty().bind(patient.telefonoProperty());
    }
}
