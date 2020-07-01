package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Salon;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;


public class VentanaSalonAddUpdateController implements Initializable {
    private App directorEscena;
    private Salon salon;

    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtCapacidad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void guardar() {
        if (txtNombre.getText().isEmpty()
            || txtDescripcion.getText().isEmpty()
            || txtCapacidad.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Salones");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, debe llenar los campos");
            alert.initOwner(null);
            alert.show();
            this.txtNombre.requestFocus();
        } else {
            if (salon != null) {
                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                alert2.setTitle("Salones");
                alert2.setHeaderText(null);
                alert2.setContentText("¿Desea actualizar este registro?");
                alert2.initOwner(null);

                Optional<ButtonType> seleccion = alert2.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    salon.setNombreSalon(txtNombre.getText());
                    salon.setDescripcion(txtDescripcion.getText());
                    salon.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                    Conexion.getInstancia().modificar(salon);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Salones");
                    alert.setHeaderText(null);
                    alert.setContentText("Registro actualizado correctamente");
                    alert.initOwner(null);
                    alert.show();
                    this.directorEscena.mostrarVentanaSalon();
                } else {
                    this.txtNombre.requestFocus();
                }
                
            } else {
                Salon salon = new Salon();
                salon.setSalonId(UUID.randomUUID().toString());
                salon.setNombreSalon(txtNombre.getText());
                salon.setDescripcion(txtDescripcion.getText());
                salon.setCapacidad(Integer.parseInt(txtCapacidad.getText()));

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Salones");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar este registro?");
                alert.initOwner(null);

                Optional<ButtonType> seleccion = alert.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    Conexion.getInstancia().agregar(salon);
                    Alert alert1 = new Alert(AlertType.INFORMATION);
                    alert1.setTitle("Salones");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Registro guardado correctamente");
                    alert1.initOwner(null);
                    alert1.show();
                    this.directorEscena.mostrarVentanaSalon();
                } else {
                    this.txtNombre.requestFocus();
                }

            }
        }
    }

    public void cancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Salones");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaSalon();
        } else {
            this.txtNombre.requestFocus();
        }
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
        this.txtNombre.setText(salon.getNombreSalon());
        this.txtDescripcion.setText(salon.getDescripcion());
        this.txtCapacidad.setText(String.valueOf(salon.getCapacidad()));
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
}