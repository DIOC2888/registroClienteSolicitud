package org.example.empresafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.example.empresafx.application.RegistroClienteApplication;
import org.example.empresafx.application.SolicitudServicioApplication;

import java.io.IOException;

public class MenuPrincipalController {
    @FXML
    private MenuItem miCliente;
    @FXML
    private MenuItem miSolicitud;


    public void clickRegistroCliente(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroClienteApplication.class.getResource("/org/example/empresafx/registro-cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Registro de Cliente");
        stage.setScene(scene);
        stage.show();
    }

    public void clickRegistroSolicitud(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SolicitudServicioApplication.class.getResource("/org/example/empresafx/solicitud-servicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Solicitud de servicio");
        stage.setScene(scene);
        stage.show();
    }
}
