package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.model.Appointment;
import uv.mx.sistemasredproject.model.Doctor;
import uv.mx.sistemasredproject.model.Patient;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AppointmentCellController implements Initializable {
    private final Appointment appointment;
    public Label idLabel;
    public Label dateLabel;
    public Label motivoLabel;
    public Label doctorNameLabel;
    public Label doctorDegreeLabel;
    public Label patientNameLabel;
    public Label patientPhoneLabel;

    public AppointmentCellController(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Doctor doctor = null;
        try {
            doctor = Model.getInstance().getMedicoService().getDoctor(appointment.getDoctorId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        Patient patient = null;
        try {
            patient = Model.getInstance().getMedicoService().getPatient(appointment.getPatientId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        String date = appointment.getDatetime().replace(":00.0", "");

        idLabel.setText(String.valueOf(appointment.getId()));
        dateLabel.setText(date);
        motivoLabel.setText(appointment.getMotivo());
        doctorNameLabel.setText(doctor.getName());
        doctorDegreeLabel.setText(doctor.getDegree());
        patientNameLabel.setText(patient.getName());
        patientPhoneLabel.setText(patient.getPhone());

    }
}
