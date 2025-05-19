package uv.mx.sistemasredproject.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.model.Medico;
import uv.mx.sistemasredproject.client.views.DoctorCellFactory;
import uv.mx.sistemasredproject.client.views.SubmenuOptions;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DoctorViewController implements Initializable {
    public Button addDoctor;
    public Button editDoctor;
    public Button deleteDoctor;

    public ListView<Medico> doctorListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            doctorListView.setItems(FXCollections.observableList(Model
                    .getInstance()
                    .getMedicoService()
                    .listarMedicos()));
        } catch (RemoteException e) {
            System.out.println("Error al listar medicos");
            throw new RuntimeException(e);
        }
        doctorListView.setCellFactory(e -> new DoctorCellFactory());
        addCellListener();
        addDoctor.setOnAction(actionEvent -> onAddDoctor());
        editDoctor.setOnAction(actionEvent -> onEditDoctor());
        deleteDoctor.setOnAction(actionEvent -> onDeleteDoctor());
    }

    private void onAddDoctor() {
        Model.getInstance().getViewFactory().getCreateDoctorDialog(null);
    }

    private void addCellListener() {
        doctorListView.getSelectionModel().selectedItemProperty().addListener((observableValue, medico, selection) ->
            {
            editDoctor.setDisable(selection == null);
            deleteDoctor.setDisable(selection == null);
            });
    }

    private void onEditDoctor() {
        Medico medico = doctorListView.getSelectionModel().getSelectedItem();
        Model.getInstance().getViewFactory().getCreateDoctorDialog(medico);
    }

    private void onDeleteDoctor() {
        Medico medico = doctorListView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar un doctor");
        alert.setHeaderText("Remover a Dr. " + medico.getNombre());
        alert.setContentText("¿Estás seguro de eliminar el registro?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Model.getInstance().getMedicoService().eliminarMedico(medico.getMedicoId());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

            // refresh view
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.DOCTORS);
        }
    }
}
