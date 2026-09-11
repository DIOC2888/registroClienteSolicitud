package org.example.empresafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.empresafx.application.RegistroClienteApplication;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class SolicitudServicioController {
    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtCorreo;
    @FXML
    private ComboBox<String> cmbTipoCliente;
    @FXML
    private TextField txtAsunto;
    @FXML
    private ComboBox<String> cmbServicio;
    @FXML
    private TextArea txtAreaDescripcion;
    @FXML
    private Button btnSelectArchivoServicio;
    @FXML
    private TextField txtArchivo;
    @FXML
    private Button btnEvidencias;
    @FXML
    private TextField txtEvidencias;

    @FXML
    private RadioButton btnRadioAlta;

    @FXML
    private RadioButton btnRadioMediana;

    @FXML
    private RadioButton btnRadioBaja;

    private ToggleGroup grupoPrioridad;

    @FXML
    public void initialize() {

        // Tipo de cliente
        cmbTipoCliente.getItems().addAll(
                "Cliente Regular",
                "Cliente Frecuente"
        );

        // Tipo de servicio
        cmbServicio.getItems().addAll(
                "Soporte técnico",
                "Mantenimiento",
                "Instalación",
                "Consultoría"
        );


        grupoPrioridad = new ToggleGroup();

        btnRadioAlta.setToggleGroup(grupoPrioridad);
        btnRadioMediana.setToggleGroup(grupoPrioridad);
        btnRadioBaja.setToggleGroup(grupoPrioridad);
    }

    public void clickLimpiarServicio(ActionEvent actionEvent) {
        txtCliente.clear();
        txtCorreo.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        txtAsunto.clear();
        cmbServicio.getSelectionModel().clearSelection();
        txtAreaDescripcion.clear();
        txtEvidencias.clear();
        txtArchivo.clear();


    }

    public void clickSolicitud(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroClienteApplication.class.getResource("/org/example/empresafx/registro-cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Registro de Cliente");
        stage.setScene(scene);
        stage.show();
    }

    public void clickGuardarServicio(ActionEvent actionEvent) {
        if(!validateForm()) {
            System.out.println("Por favor, complete todos los campos antes de guardar.");
            return;
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            System.out.println("Cliente registrado: " + txtCliente.getText());
            alert.setTitle("Guardado");
            alert.setHeaderText("Guardado");
            alert.setContentText("El cliente ha sido registrado correctamente.");
            alert.showAndWait();

        }


    }

    private boolean validateForm() {

        if (
                txtCliente.getText().isEmpty() ||
                        txtArchivo.getText().isEmpty() ||
                        txtEvidencias.getText().isEmpty() ||
                        txtCorreo.getText().isEmpty() ||
                        txtAsunto.getText().isEmpty() ||
                        cmbTipoCliente.getValue() == null ||
                        cmbServicio.getValue() == null ||
                        txtAreaDescripcion.getText().isEmpty() ||
                        grupoPrioridad.getSelectedToggle() == null
        ) {

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Formulario incompleto");
            alert.setHeaderText(null);

            alert.setContentText(
                    "Por favor, complete todos los campos antes de guardar."
            );

            alert.showAndWait();

            return false;
        }

        return true;
    }

    public void clickExaminarEvidencias(ActionEvent actionEvent) {

        DirectoryChooser dc = new DirectoryChooser();

        dc.setTitle("Seleccionar carpeta de evidencias");

        dc.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        File f = dc.showDialog(
                btnEvidencias.getScene().getWindow()
        );

        if (f != null) {
            txtEvidencias.setText(f.getAbsolutePath());
        }
    }

    public void clickExaminarArchivoServicio(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();

        fc.setTitle("Seleccionar archivo");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"),
                new FileChooser.ExtensionFilter("Word", "*.docx"),
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        File f = fc.showOpenDialog(
                btnSelectArchivoServicio.getScene().getWindow()
        );

        if (f != null) {
            txtArchivo.setText(f.getAbsolutePath());
        }
    }
}
