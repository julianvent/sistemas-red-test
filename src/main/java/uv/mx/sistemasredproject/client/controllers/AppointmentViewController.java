package uv.mx.sistemasredproject.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.client.views.AppointmentCellFactory;
import uv.mx.sistemasredproject.model.Cita;
import uv.mx.sistemasredproject.server.models.ServerModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {
    public Button add;
    public Button edit;
    public Button delete;
    public ListView<Cita> appointmentListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentListView.setItems(FXCollections.observableList(ServerModel
                .getInstance()
                .getDatabaseDriver()
                .obtenerCitas()));
        add.setOnAction(actionEvent -> onAdd());
        appointmentListView.setCellFactory(edit -> new AppointmentCellFactory());
    }

    private void onAdd() {
        Model.getInstance().getViewFactory().getCreateAppointmentDialog(null);
    }
}
