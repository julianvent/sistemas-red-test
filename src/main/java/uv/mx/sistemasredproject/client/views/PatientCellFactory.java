package uv.mx.sistemasredproject.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import uv.mx.sistemasredproject.client.controllers.PatientCellController;
import uv.mx.sistemasredproject.model.Patient;

import java.io.IOException;

public class PatientCellFactory extends ListCell<Patient> {
    @Override
    protected void updateItem(Patient item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/patient-cell.fxml"));
            PatientCellController controller = new PatientCellController(item);
            loader.setController(controller);
            setText(null);

            try {
                setGraphic(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
