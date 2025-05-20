package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;
import uv.mx.sistemasredproject.model.Patient;
import uv.mx.sistemasredproject.utils.Validador;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class CreatePatientController implements Initializable {
    final Patient patient;
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

    public CreatePatientController(Patient patient) {
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

        if (proceed) {
            if (patient != null) {
                System.out.println("Actualizando paciente...");
                try {
                    Model.getInstance().getMedicoService().updatePatient(
                            patient.getId(),
                            name,
                            curp,
                            phone
                    );
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Agregando paciente...");
                try {
                    Model.getInstance().getMedicoService().addPatient(name, curp, phone, email);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

            // close dialog
            Stage stage = (Stage) createButton.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);

            // refresh view
            Model.getInstance().setAllPatients();
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
            nameField.setText(patient.getName());
            curpField.setText(patient.getCurp());
            phoneField.setText(patient.getPhone());
            emailField.setText(patient.getEmail());
        }
    }
}
