package uv.mx.sistemasredproject.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import uv.mx.sistemasredproject.model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateDoctorController implements Initializable {
    public Button createButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.setOnAction(actionEvent -> onCreate());
    }

    private void onCreate() {
        Stage stage = (Stage)createButton.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
