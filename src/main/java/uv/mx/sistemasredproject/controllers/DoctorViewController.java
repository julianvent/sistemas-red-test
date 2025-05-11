package uv.mx.sistemasredproject.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import uv.mx.sistemasredproject.model.Model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorViewController implements Initializable {
    public Button addDoctor;
    public Button editDoctor;
    public Button deleteDoctor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDoctor.setOnAction(actionEvent ->
            {
            try {
                onAddDoctor();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            });
    }

    private void onAddDoctor() throws IOException {
        Model.getInstance().getViewFactory().showCreateDoctorView();
    }
}
