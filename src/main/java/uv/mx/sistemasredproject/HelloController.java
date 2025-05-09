package uv.mx.sistemasredproject;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Label welcomeText;
    public Button helloBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloBtn.setOnAction(actionEvent -> onHelloButtonClick());
    }

    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}