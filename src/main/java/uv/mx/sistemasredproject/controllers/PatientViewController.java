package uv.mx.sistemasredproject.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import uv.mx.sistemasredproject.server.models.Paciente;
import uv.mx.sistemasredproject.server.models.ServerModel;
import uv.mx.sistemasredproject.views.PatientCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientViewController implements Initializable {
    public Button add;
    public Button edit;
    public Button delete;
    public ListView<Paciente> patientsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientsListView.setItems(FXCollections.observableList(ServerModel
                .getInstance()
                .getDatabaseDriver()
                .obtenerPacientes()));
        patientsListView.setCellFactory(e -> new PatientCellFactory());
    }
}
