package uv.mx.sistemasredproject.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import uv.mx.sistemasredproject.client.controllers.DoctorCellController;
import uv.mx.sistemasredproject.model.Doctor;

import java.io.IOException;

public class DoctorCellFactory extends ListCell<Doctor> {
    @Override
    protected void updateItem(Doctor item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/doctor-cell.fxml"));
            DoctorCellController controller = new DoctorCellController(item);
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
