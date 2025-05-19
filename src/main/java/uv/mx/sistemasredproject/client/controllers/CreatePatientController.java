package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;
import uv.mx.sistemasredproject.model.Paciente;
import uv.mx.sistemasredproject.server.models.ServerModel;
import uv.mx.sistemasredproject.utils.Validador;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatePatientController implements Initializable {
    final Paciente patient;
    public TextField nameField;
    public Label nameWarning;
    public TextField curpField;
    public Label curpWarning;
    public TextField phoneField;
    public Label phoneWarning;
    public TextField emailField;
    public Label emailWarning;
    public Button createButton;
    public Button cancelButton;

    public CreatePatientController(Paciente patient) {
        this.patient = patient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeData();
        createButton.setOnAction(actionEvent -> onCreate());
        cancelButton.setOnAction(actionEvent -> onCancel());
    }

    private void onCreate() {
        String name = nameField.getText().trim();
        String curp = curpField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        curpWarning.setText(Validador.validarCurp(curp));
        phoneWarning.setText(Validador.validarTelefono(phone));
        emailWarning.setText(Validador.validarCorreo(email));

        boolean proceed = curpWarning.getText().isBlank() && phoneWarning.getText().isBlank() && emailWarning
                .getText()
                .isBlank();
        System.out.println((proceed) ? "OK " : "ABORT " + curpWarning.getText());

        if (proceed) {
            if (patient != null) {
                System.out.println("Actualizando paciente...");
                ServerModel.getInstance().getDatabaseDriver().actualizarPaciente(
                        patient.getPacienteId(),
                        name,
                        curp,
                        phone
                );
            } else {
                System.out.println("Agregando paciente...");
                ServerModel.getInstance().getDatabaseDriver().agregarPaciente(name, curp, phone, email);
            }

            // close dialog
            Stage stage = (Stage) createButton.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);

            // refresh view
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.PATIENTS);
        }
    }

    private void onCancel() {
        Stage stage = (Stage) createButton.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    private void initializeData() {
        if (patient != null) {
            nameField.setText(patient.getNombre());
            curpField.setText(patient.getCurp());
            phoneField.setText(patient.getTelefono());
            emailField.setText(patient.getCorreoElectronico());
        }
    }
}
