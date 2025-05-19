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
        appointmentListView.setCellFactory(edit -> new AppointmentCellFactory());
        setCellListener();

        add.setOnAction(actionEvent -> onAdd());
        edit.setOnAction(actionEvent -> onEdit());
    }

    private void setCellListener() {
        appointmentListView.getSelectionModel().selectedItemProperty().addListener((observableValue, cita, selection) ->
            {
            edit.setDisable(selection == null);
            delete.setDisable(selection == null);
            });
    }

    private void onAdd() {
        Model.getInstance().getViewFactory().getCreateAppointmentDialog(null);
    }

    private void onEdit() {
        Cita appointment = appointmentListView.getSelectionModel().getSelectedItem();
        Model.getInstance().getViewFactory().getCreateAppointmentDialog(appointment);
    }
}
