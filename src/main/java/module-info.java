module org.example.empresafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.empresafx to javafx.fxml;
    exports org.example.empresafx;
    opens org.example.empresafx.application to javafx.fxml;
    exports org.example.empresafx.application;
    opens org.example.empresafx.controller to javafx.fxml;
    exports org.example.empresafx.controller;
}