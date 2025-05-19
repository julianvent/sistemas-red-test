package uv.mx.sistemasredproject.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import uv.mx.sistemasredproject.controllers.DoctorCellController;
import uv.mx.sistemasredproject.server.models.Medico;

import java.io.IOException;

public class DoctorCellFactory extends ListCell<Medico> {
    @Override
    protected void updateItem(Medico item, boolean empty) {
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
