package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.model.Doctor;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;
import uv.mx.sistemasredproject.utils.Validador;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class CreateDoctorController implements Initializable {
    private final Doctor doctor;
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

    public CreateDoctorController(Doctor doctor) {
        this.doctor = doctor;
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
            if (doctor != null) { // update
                try {
                    Model.getInstance().getMedicoService().updateDoctor(
                            doctor.getId(),
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
                    Model.getInstance().getMedicoService().addDoctor(name, degree, cedula, email);
                } catch (RemoteException e) {
                    throw new RuntimeException("Error del servidor al actualizar medico", e);
                }
            }

            // close dialog
            Stage stage = (Stage) createButton.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);

            // refresh view
            Model.getInstance().setAllDoctors();
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.DOCTORS);
        }
    }

    private void onCancel() {
        Stage stage = (Stage) createButton.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    private void initializeData() {
        if (doctor != null) {
            nameField.setText(doctor.getName());
            degreeField.setText(doctor.getDegree());
            cedulaField.setText(doctor.getCedula());
            emailField.setText(doctor.getEmail());
        }
    }
}
