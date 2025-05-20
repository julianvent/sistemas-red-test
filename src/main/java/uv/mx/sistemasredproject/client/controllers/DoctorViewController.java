package uv.mx.sistemasredproject.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import uv.mx.sistemasredproject.client.model.Model;
import uv.mx.sistemasredproject.model.Doctor;
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

    public ListView<Doctor> doctorListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeAllDoctors();
        doctorListView.setItems(Model.getInstance().getDoctorList());
        doctorListView.setCellFactory(e -> new DoctorCellFactory());
        addCellListener();
        addDoctor.setOnAction(actionEvent -> onAddDoctor());
        editDoctor.setOnAction(actionEvent -> onEditDoctor());
        deleteDoctor.setOnAction(actionEvent -> onDeleteDoctor());
    }

    private void initializeAllDoctors() {
        if (Model.getInstance().getDoctorList().isEmpty()) {
            Model.getInstance().setAllDoctors();
        }
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
        Doctor doctor = doctorListView.getSelectionModel().getSelectedItem();
        Model.getInstance().getViewFactory().getCreateDoctorDialog(doctor);
    }

    private void onDeleteDoctor() {
        Doctor doctor = doctorListView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar un doctor");
        alert.setHeaderText("Remover a Dr. " + doctor.getName());
        alert.setContentText("¿Estás seguro de eliminar el registro?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Model.getInstance().getMedicoService().deleteDoctor(doctor.getId());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

            // refresh view
            Model.getInstance().setAllDoctors();
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.REFRESH);
            Model.getInstance().getViewFactory().selectedMenuItemProperty().set(SubmenuOptions.DOCTORS);
        }
    }
}
