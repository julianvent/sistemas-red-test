module uv.mx.sistemasredproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.rmi;
    requires java.desktop;


    opens uv.mx.sistemasredproject to javafx.fxml;
    exports uv.mx.sistemasredproject;
    exports uv.mx.sistemasredproject.client.views;
    exports uv.mx.sistemasredproject.client.controllers;
    exports uv.mx.sistemasredproject.interfaces;
    exports uv.mx.sistemasredproject.server.rmi;
    exports uv.mx.sistemasredproject.server.models;
    exports uv.mx.sistemasredproject.model;
    exports uv.mx.sistemasredproject.client.model;
}