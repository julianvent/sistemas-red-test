package uv.mx.sistemasredproject.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import uv.mx.sistemasredproject.client.controllers.CustomDoctorCellController;
import uv.mx.sistemasredproject.model.Medico;

import java.io.IOException;

public class CustomDoctorCellFactory extends ListCell<Medico> {
    @Override
    protected void updateItem(Medico item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/custom-doctor-cell.fxml"));
            CustomDoctorCellController controller = new CustomDoctorCellController(item);
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
