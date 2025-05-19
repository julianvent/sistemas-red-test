package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.model.Medico;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;
import uv.mx.sistemasredproject.utils.Validador;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class CreateDoctorController implements Initializable {
    private final Medico medico;
    public Button createButton;
    public Button cancelButton;
    public TextField nameField;
    public TextField degreeField;
    public TextField cedulaField;
    public TextField emailField;
    public Label nameWarning;
    public Label degreeWarning;
    public Label cedulaWarning;
    public Label emailWarning;

    public CreateDoctorController(Medico medico) {
        this.medico = medico;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.setOnAction(actionEvent -> onCreate());
        cancelButton.setOnAction(actionEvent -> onCancel());
        initializeData();
    }

    private void onCreate() {
        String name = nameField.getText().trim();
        String degree = degreeField.getText().trim();
        String cedula = cedulaField.getText().trim();
        String email = emailField.getText().trim();

        nameWarning.setText(Validador.validateRequiredField(name));
        degreeWarning.setText(Validador.validateRequiredField(degree));
        cedulaWarning.setText(Validador.validateRequiredField(cedula));
        emailWarning.setText(Validador.validarCorreo(email));

        boolean proceed = nameWarning.getText().isBlank() && degreeWarning.getText().isBlank() && cedulaWarning
                .getText()
                .isBlank() && emailWarning.getText().isBlank();

        if (proceed) {
            if (medico != null) { // update
                try {
                    Model.getInstance().getMedicoService().actualizarMedico(
                            medico.getMedicoId(),
                            name,
                            degree,
                            cedula,
                            email
                    );
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            } else { // create
                try {
                    Model.getInstance().getMedicoService().agregarMedico(name, degree, cedula, email);
                } catch (RemoteException e) {
                    throw new RuntimeException("Error del servidor al actualizar medico", e);
                }
            }

            // close dialog
            Stage stage = (Stage) createButton.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);

            // refresh view
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.DOCTORS);
        }
    }

    private void onCancel() {
        Stage stage = (Stage) createButton.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    private void initializeData() {
        if (medico != null) {
            nameField.setText(medico.getNombre());
            degreeField.setText(medico.getEspecialidad());
            cedulaField.setText(medico.getCedula());
            emailField.setText(medico.getCorreoElectronico());
        }
    }
}
