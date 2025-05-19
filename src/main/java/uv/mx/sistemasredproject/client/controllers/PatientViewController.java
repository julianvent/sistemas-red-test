package uv.mx.sistemasredproject.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;
import uv.mx.sistemasredproject.model.Paciente;
import uv.mx.sistemasredproject.server.models.ServerModel;
import uv.mx.sistemasredproject.client.views.PatientCellFactory;

import java.net.URL;
import java.util.Optional;
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
        addCellListener();
        add.setOnAction(actionEvent -> onAdd());
        edit.setOnAction(actionEvent -> onEdit());
        delete.setOnAction(actionEvent -> onDelete());
    }

    private void addCellListener() {
        patientsListView.getSelectionModel().selectedItemProperty().addListener((observableValue, medico, selection) ->
            {
            edit.setDisable(selection == null);
            delete.setDisable(selection == null);
            });
    }

    private void onAdd() {
        Model.getInstance().getViewFactory().getCreatePatientDialog(null);
    }

    private void onEdit() {
        Paciente patient = patientsListView.getSelectionModel().getSelectedItem();
        Model.getInstance().getViewFactory().getCreatePatientDialog(patient);
    }

    private void onDelete() {
        Paciente patient = patientsListView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar registro de paciente");
        alert.setHeaderText("Remover a " + patient.getNombre());
        alert.setContentText("¿Estás seguro de eliminar el registro?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Eliminando a " + patient.getNombre());
            ServerModel.getInstance().getDatabaseDriver().eliminarPaciente(patient.getPacienteId());

            // refresh view
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.PATIENTS);
        }
    }
}
