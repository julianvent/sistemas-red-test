package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.client.views.AppointmentCellFactory;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;
import uv.mx.sistemasredproject.model.Appointment;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {
    public Button add;
    public Button edit;
    public Button delete;
    public ListView<Appointment> appointmentListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeAppointmentList();
        appointmentListView.setItems(Model.getInstance().getAppointmentList());
        appointmentListView.setCellFactory(edit -> new AppointmentCellFactory());
        setCellListener();

        add.setOnAction(actionEvent -> onAdd());
        edit.setOnAction(actionEvent -> onEdit());
        delete.setOnAction(actionEvent -> onDelete());
    }

    private void initializeAppointmentList() {
        if (Model.getInstance().getAppointmentList().isEmpty()) {
            Model.getInstance().setAllAppointments();
        }
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
        Appointment appointment = appointmentListView.getSelectionModel().getSelectedItem();
        Model.getInstance().getViewFactory().getCreateAppointmentDialog(appointment);
    }

    private void onDelete() {
        Appointment appointment = appointmentListView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar una cita");
        alert.setHeaderText("Eliminar cita");
        alert.setContentText("¿Estás seguro de eliminar la cita?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Model.getInstance().getMedicoService().deleteAppointment(appointment.getId());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

            // refresh view
            Model.getInstance().setAllAppointments();
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.APPOINTMENTS);
        }
    }
}
