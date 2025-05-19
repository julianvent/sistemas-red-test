package uv.mx.sistemasredproject.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.client.views.CustomDoctorCellFactory;
import uv.mx.sistemasredproject.client.views.CustomPatientCellFactory;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;
import uv.mx.sistemasredproject.model.Cita;
import uv.mx.sistemasredproject.model.Medico;
import uv.mx.sistemasredproject.model.Paciente;
import uv.mx.sistemasredproject.utils.Validador;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateAppointmentController implements Initializable {
    private final Cita appointment;
    public DatePicker datePicker;
    public Spinner<Integer> hourSpinner;
    public Spinner<Integer> minutesSpinner;
    public TextArea motivoField;
    public ListView<Medico> doctorsListView;
    public ListView<Paciente> patientsListView;
    public Button createButton;
    public Button cancelButton;
    public Label dateWarning;
    public Label motivoWarning;
    public Label doctorWarning;
    public Label patientWarning;

    public CreateAppointmentController(Cita appointment) {
        this.appointment = appointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            doctorsListView.setItems(FXCollections.observableList(Model
                    .getInstance()
                    .getMedicoService()
                    .listarMedicos()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        doctorsListView.setCellFactory(e -> new CustomDoctorCellFactory());

        try {
            patientsListView.setItems(FXCollections.observableList(Model
                    .getInstance()
                    .getMedicoService()
                    .listarPacientes()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        patientsListView.setCellFactory(e -> new CustomPatientCellFactory());

        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 17));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0, 30));
        createButton.setOnAction(actionEvent -> onCreate());
        cancelButton.setOnAction(actionEvent -> onCancel());

        initializeData();
    }

    private void onCreate() {
        LocalDate date = datePicker.getValue();
        int hours = hourSpinner.getValue();
        int minutes = minutesSpinner.getValue();
        String motivo = motivoField.getText();
        Medico doctor = doctorsListView.getSelectionModel().getSelectedItem();
        Paciente patient = patientsListView.getSelectionModel().getSelectedItem();

        dateWarning.setText(Validador.validateNull(date));
        motivoWarning.setText(Validador.validateRequiredField(motivo));


        boolean proceed = dateWarning.getText().isBlank() && motivoWarning.getText().isBlank();

        if (proceed) {
            Timestamp timestamp = Timestamp.valueOf(date.atTime(hours, minutes));
            if (appointment != null) {
                int doctorId = (doctor == null) ? appointment.getMedicoId() : doctor.getMedicoId();

                System.out.println("Actualizando cita...");
                try {
                    Model.getInstance().getMedicoService().actualizarCita(
                            appointment.getCitaId(),
                            timestamp.toString(),
                            motivo,
                            doctorId
                    );
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            } else {
                patientWarning.setText(Validador.validateNull(patient));
                doctorWarning.setText(Validador.validateNull(doctor));
                if (!patientWarning.getText().isBlank() && doctorWarning.getText().isBlank()) return;

                System.out.println("Creando cita...");
                try {
                    Model.getInstance().getMedicoService().agregarCita(
                            timestamp.toString(),
                            motivo,
                            doctor.getMedicoId(),
                            patient.getPacienteId()
                    );
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            // close dialog
            Stage stage = (Stage) createButton.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);

            // refresh view
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.APPOINTMENTS);
        }
    }

    private void onCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    private void initializeData() {
        if (appointment != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

            LocalDateTime dateTime = LocalDateTime.parse(appointment.getFechaHora(), formatter);

            datePicker.setValue(dateTime.toLocalDate());
            hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                    9,
                    17,
                    dateTime.getHour()
            ));
            minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                    0,
                    30,
                    dateTime.getMinute(),
                    30
            ));

            motivoField.setText(appointment.getMotivo());
            patientsListView.setDisable(true);
        }
    }
}
