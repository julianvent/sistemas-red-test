package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.client.views.CustomDoctorCellFactory;
import uv.mx.sistemasredproject.client.views.CustomPatientCellFactory;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;
import uv.mx.sistemasredproject.model.Appointment;
import uv.mx.sistemasredproject.model.Doctor;
import uv.mx.sistemasredproject.model.Patient;
import uv.mx.sistemasredproject.utils.Validador;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateAppointmentController implements Initializable {
    private final Appointment appointment;
    public DatePicker datePicker;
    public Spinner<Integer> hourSpinner;
    public Spinner<Integer> minutesSpinner;
    public TextArea motivoField;
    public ListView<Doctor> doctorsListView;
    public ListView<Patient> patientsListView;
    public Button createButton;
    public Button cancelButton;
    public Label dateWarning;
    public Label motivoWarning;
    public Label doctorWarning;
    public Label patientWarning;

    public CreateAppointmentController(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeLists();
        doctorsListView.setItems(Model.getInstance().getDoctorList());
        doctorsListView.setCellFactory(e -> new CustomDoctorCellFactory());

        patientsListView.setItems(Model.getInstance().getPatientList());
        patientsListView.setCellFactory(e -> new CustomPatientCellFactory());

        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 17));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0, 30));
        createButton.setOnAction(actionEvent -> onCreate());
        cancelButton.setOnAction(actionEvent -> onCancel());

        initializeData();
    }

    private void initializeLists() {
        if (Model.getInstance().getPatientList().isEmpty()) {
            Model.getInstance().setAllPatients();
        }
        if (Model.getInstance().getDoctorList().isEmpty()) {
            Model.getInstance().setAllDoctors();
        }
    }

    private void onCreate() {
        LocalDate date = datePicker.getValue();
        int hours = hourSpinner.getValue();
        int minutes = minutesSpinner.getValue();
        String motivo = motivoField.getText();
        Doctor doctor = doctorsListView.getSelectionModel().getSelectedItem();
        Patient patient = patientsListView.getSelectionModel().getSelectedItem();

        dateWarning.setText(Validador.validateNull(date));
        motivoWarning.setText(Validador.validateRequiredField(motivo));


        boolean proceed = dateWarning.getText().isBlank() && motivoWarning.getText().isBlank();

        if (proceed) {
            Timestamp timestamp = Timestamp.valueOf(date.atTime(hours, minutes));
            if (appointment != null) {
                int doctorId = (doctor == null) ? appointment.getDoctorId() : doctor.getId();

                System.out.println("Actualizando cita...");
                try {
                    Model.getInstance().getMedicoService().updateAppointment(
                            appointment.getId(),
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
                    Model.getInstance().getMedicoService().addAppointment(
                            timestamp.toString(),
                            motivo,
                            doctor.getId(),
                            patient.getId()
                    );
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            // close dialog
            Stage stage = (Stage) createButton.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);

            // refresh view
            Model.getInstance().setAllAppointments();
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

            LocalDateTime dateTime = LocalDateTime.parse(appointment.getDatetime(), formatter);

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
