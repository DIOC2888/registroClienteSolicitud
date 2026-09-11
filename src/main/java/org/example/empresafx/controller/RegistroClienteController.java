package org.example.empresafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.empresafx.application.RegistroClienteApplication;

import java.io.File;
import java.io.IOException;

public class RegistroClienteController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TextField txtDirectorio;
    @FXML
    private Button btnExaminarIdentificacion;
    @FXML
    private Button btnExaminarDirectorio;
    @FXML
    private ComboBox<String> cmbCliente;

    @FXML
    public void initialize() {
        cmbCliente.setItems(
                FXCollections.observableArrayList(
                        "Cliente Regular",
                        "Cliente Frecuente"
                )
        );
    }

    public void clickExaminarIdentificacion(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar archivo de requerimientos");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*"),
                new FileChooser.ExtensionFilter("Word", "*.docx*")
        );
        File f = fc.showOpenDialog(btnExaminarIdentificacion.getScene().getWindow());

        if (f != null) {
            txtIdentificacion.setText(f.getAbsolutePath());
        }

    }

    public void clickExaminarDirectorio(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccionar directorio");
        File f = dc.showDialog(btnExaminarDirectorio.getScene().getWindow());
        dc.setInitialDirectory(new File(System.getProperty("user.home")));

        if (f != null) {
            txtDirectorio.setText(f.getAbsolutePath());
        }
    }

    public void clickGuardarCliente(ActionEvent actionEvent) {
        if (!validateForm()) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        System.out.println("Proyecto guardado: " + txtNombre.getText());
        alert.setTitle("Guardado");
        alert.setHeaderText("Guardado");
        alert.setContentText("El proyecto ha sido guardado correctamente.");
        alert.showAndWait();

        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        String tipoCliente = cmbCliente.getValue();
        String identificacion = txtIdentificacion.getText();
        String directorio = txtDirectorio.getText();

    }

    public void clickLimpiar(ActionEvent actionEvent) {
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        cmbCliente.setValue(null);
        txtIdentificacion.clear();
        txtDirectorio.clear();
    }

    public void clickCrearSolicitud(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroClienteApplication.class.getResource("/org/example/empresafx/solicitud-servicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Solicitud de servicio");
        stage.setScene(scene);
        stage.show();
    }


    private boolean validateForm(){if (txtNombre.getText().isEmpty() ||
            txtIdentificacion.getText().isEmpty() ||
            txtTelefono.getText().isEmpty() ||
            txtCorreo.getText().isEmpty() ||
            txtDirectorio.getText().isEmpty() ||
            cmbCliente.getValue() == null)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Formulario incompleto!");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, complete todos los campos antes de guardar.");
        alert.showAndWait();
        return false;
    }
        return true;
    }
}
