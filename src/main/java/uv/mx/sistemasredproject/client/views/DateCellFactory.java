package uv.mx.sistemasredproject.client.views;

import javafx.scene.control.DateCell;

import java.time.LocalDate;

public class DateCellFactory extends DateCell {
    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);
        LocalDate now = LocalDate.now().plusDays(1);

        if (item.isBefore(now)) {
            setDisable(true);
            setStyle("-fx-background-color: #EEEEEE;"); // color rosado para fechas deshabilitadas
        }
    }
}
