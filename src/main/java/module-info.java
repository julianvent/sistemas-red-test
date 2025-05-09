module uv.mx.sistemasredproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens uv.mx.sistemasredproject to javafx.fxml;
    exports uv.mx.sistemasredproject;
    exports uv.mx.sistemasredproject.views;
}