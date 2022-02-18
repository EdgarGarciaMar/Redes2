module com.ipn.mx.practica1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;

    opens com.ipn.mx.practica1 to javafx.fxml;
    exports com.ipn.mx.practica1;
}
