package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.CarreraTecnica;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;


public class VentanaCarreraTecnicaAddUpdateController implements Initializable {
    private App directorEscena;
    private CarreraTecnica carreraTecnica;

    @FXML private TextField txtNombre;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.txtNombre.requestFocus();

        this.txtNombre.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));
    }

    public void guardar() {
        if (txtNombre.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Carreras Técnicas");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingrese nombre");
            alert.initOwner(null);
            alert.show();
            this.txtNombre.requestFocus();
        } else {
            if (carreraTecnica != null) {
                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                alert2.setTitle("Carreras Técnicas");
                alert2.setHeaderText(null);
                alert2.setContentText("¿Desea actualizar este registro?");
                alert2.initOwner(null);

                Optional<ButtonType> seleccion = alert2.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    carreraTecnica.setNombre(txtNombre.getText());
                    Conexion.getInstancia().modificar(carreraTecnica);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Carreras Técnicas");
                    alert.setHeaderText(null);
                    alert.setContentText("Registro actualizado correctamente");
                    alert.initOwner(null);
                    alert.show();
                    this.directorEscena.mostrarVentanaCarrera();
                } else {
                    this.txtNombre.requestFocus();
                }
                
            } else {
                CarreraTecnica carreraTecnica = new CarreraTecnica();
                carreraTecnica.setCodigoCarrera(UUID.randomUUID().toString());
                carreraTecnica.setNombre(txtNombre.getText());

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Carreras Técnicas");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar este registro?");
                alert.initOwner(null);

                Optional<ButtonType> seleccion = alert.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    Conexion.getInstancia().agregar(carreraTecnica);
                    Alert alert1 = new Alert(AlertType.INFORMATION);
                    alert1.setTitle("Carreras Técnicas");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Registro guardado correctamente");
                    alert1.initOwner(null);
                    alert1.show();
                    this.directorEscena.mostrarVentanaCarrera();
                } else {
                    this.txtNombre.requestFocus();
                }

            }
        }
    }

    public void cancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Carreras Técnicas");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaCarrera();
        } else {
            this.txtNombre.requestFocus();
        }
    }

    public CarreraTecnica getCarreraTecnica() {
        return carreraTecnica;
    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
        this.txtNombre.setText(carreraTecnica.getNombre());
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
}