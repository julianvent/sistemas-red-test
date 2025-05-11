package uv.mx.sistemasredproject.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import uv.mx.sistemasredproject.model.Model;
import uv.mx.sistemasredproject.server.models.Medico;
import uv.mx.sistemasredproject.server.models.ServerModel;
import uv.mx.sistemasredproject.views.SubmenuOptions;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DoctorViewController implements Initializable {
    public Button addDoctor;
    public Button editDoctor;
    public Button deleteDoctor;

    public TableView<Medico> doctors;
    public TableColumn<Medico, String> idColumn;
    public TableColumn<Medico, String> nameColumn;
    public TableColumn<Medico, String> degreeColumn;
    public TableColumn<Medico, String> cedulaColumn;
    public TableColumn<Medico, String> emailColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeData();
        setTableRowListener();
        setProperties();
        addDoctor.setOnAction(actionEvent -> onAddDoctor());
        editDoctor.setOnAction(actionEvent -> onEditDoctor());
        deleteDoctor.setOnAction(actionEvent -> onDeleteDoctor());
    }

    private void onAddDoctor() {
        Model.getInstance().getViewFactory().showCreateDoctorView(null);
    }

    private void initializeData() {
        doctors.setItems(FXCollections.observableList(ServerModel.getInstance().getDatabaseDriver().obtenerMedicos()));
    }

    private void setProperties() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("medicoId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        degreeColumn.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        cedulaColumn.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
    }

    private void setTableRowListener() {
        doctors.getSelectionModel().selectedItemProperty().addListener((observableValue, medico, newSelection) ->
            {
            editDoctor.setDisable(newSelection == null);
            deleteDoctor.setDisable(newSelection == null);
            });
    }

    private void onEditDoctor() {
        Medico medico = doctors.getSelectionModel().getSelectedItem();
        Model.getInstance().getViewFactory().showCreateDoctorView(medico);
    }

    private void onDeleteDoctor() {
        int medicoId = doctors.getSelectionModel().getSelectedItem().getMedicoId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar doctor");
        alert.setHeaderText("Eliminar doctor");
        alert.setContentText("¿Estás seguro de eliminar el registro?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            ServerModel.getInstance().getDatabaseDriver().eliminarMedico(medicoId);

            // refresh view
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.DOCTORS);
        }

    }
}
