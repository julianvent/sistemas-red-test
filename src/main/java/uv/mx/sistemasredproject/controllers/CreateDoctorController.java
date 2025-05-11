package uv.mx.sistemasredproject.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.model.Model;
import uv.mx.sistemasredproject.server.models.Medico;
import uv.mx.sistemasredproject.server.models.ServerModel;
import uv.mx.sistemasredproject.views.SubmenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

import static uv.mx.sistemasredproject.utils.Validador.validateField;

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

        boolean proceed = validateField(name, nameWarning);
        proceed &= validateField(degree, degreeWarning);
        proceed &= validateField(cedula, cedulaWarning);
        proceed &= validateField(email, emailWarning);

        if (proceed) {
            if (medico != null) { // update
                ServerModel.getInstance().getDatabaseDriver().actualizarMedico(
                        medico.getMedicoId(),
                        name,
                        degree,
                        cedula,
                        email
                );
            } else { // create
                ServerModel.getInstance().getDatabaseDriver().agregarMedico(name, degree, cedula, email);
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
            degreeField.setText(medico.getCedula());
            cedulaField.setText(medico.getCedula());
            emailField.setText(medico.getCorreoElectronico());
        }
    }
}
