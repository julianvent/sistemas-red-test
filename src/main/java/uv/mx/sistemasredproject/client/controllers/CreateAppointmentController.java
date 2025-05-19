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
import uv.mx.sistemasredproject.server.models.ServerModel;
import uv.mx.sistemasredproject.utils.Validador;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
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
        doctorsListView.setItems(FXCollections.observableList(ServerModel
                .getInstance()
                .getDatabaseDriver()
                .obtenerMedicos()));
        doctorsListView.setCellFactory(e -> new CustomDoctorCellFactory());

        patientsListView.setItems(FXCollections.observableList(ServerModel
                .getInstance()
                .getDatabaseDriver()
                .obtenerPacientes()));
        patientsListView.setCellFactory(e -> new CustomPatientCellFactory());

        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 17));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0, 30));
        createButton.setOnAction(actionEvent -> onCreate());
        cancelButton.setOnAction(actionEvent -> onCancel());
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
        doctorWarning.setText(Validador.validateNull(doctor));
        patientWarning.setText(Validador.validateNull(patient));

        boolean proceed = dateWarning.getText().isBlank() && motivoWarning.getText().isBlank() && doctorWarning
                .getText()
                .isBlank() && patientWarning.getText().isBlank();

        if (proceed) {
            Timestamp timestamp = Timestamp.valueOf(date.atTime(hours, minutes));
            if (appointment != null) {
                System.out.println("Actualizando cita...");
                ServerModel.getInstance().getDatabaseDriver().actualizarCita(
                        appointment.getCitaId(),
                        timestamp.toString(),
                        motivo,
                        doctor.getMedicoId()
                );
            } else {
                System.out.println("Creando cita...");
                ServerModel.getInstance().getDatabaseDriver().agregarCita(
                        timestamp.toString(),
                        motivo,
                        doctor.getMedicoId(),
                        patient.getPacienteId()
                );
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
}
