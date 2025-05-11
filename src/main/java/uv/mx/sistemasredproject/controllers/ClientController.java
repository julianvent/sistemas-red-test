package uv.mx.sistemasredproject.controllers;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import uv.mx.sistemasredproject.model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public BorderPane clientParent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model
                .getInstance()
                .getViewFactory()
                .selectedMenuItemProperty()
                .addListener((observableValue, submenuOptions, newValue) ->
                    {
                    switch (newValue) {
                        case DOCTORS -> clientParent.setCenter(Model.getInstance().getViewFactory().getDoctorsView());
                        default -> clientParent.setCenter(Model.getInstance().getViewFactory().getRefresh());
                    }
                    });
    }
}
