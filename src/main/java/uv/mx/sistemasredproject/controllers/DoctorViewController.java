package uv.mx.sistemasredproject.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import uv.mx.sistemasredproject.model.Model;
import uv.mx.sistemasredproject.views.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorViewController implements Initializable {
    public Button addDoctor;
    public Button editDoctor;
    public Button deleteDoctor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDoctor.setOnAction(actionEvent -> onAddDoctor());
    }

    private void onAddDoctor() {
        Model.getInstance().getViewFactory().showAddDoctorView();
    }
}
