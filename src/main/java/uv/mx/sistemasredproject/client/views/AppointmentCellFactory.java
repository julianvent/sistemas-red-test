package uv.mx.sistemasredproject.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import uv.mx.sistemasredproject.client.controllers.AppointmentCellController;
import uv.mx.sistemasredproject.model.Appointment;

import java.io.IOException;

public class AppointmentCellFactory extends ListCell<Appointment> {
    @Override
    protected void updateItem(Appointment item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/appointment-cell.fxml"));
            AppointmentCellController controller = new AppointmentCellController(item);
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
