package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


public class VentanaPrincipalController implements Initializable {
    private App directorEscena;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void mostrarVentanaCarrera() {
        this.directorEscena.mostrarVentanaCarrera();
    }

    public void mostrarVentanaInstructor() {
        this.directorEscena.mostrarVentanaInstructor();
    }

    public void mostrarVentanaSalon() {
        this.directorEscena.mostrarVentanaSalon();
    }

    public void mostrarVentanaHorario() {
        this.directorEscena.mostrarVentanaHorario();
    }

    public void mostrarVentanaAlumno() {
        this.directorEscena.mostrarVentanaAlumno();
    }

    public void mostrarVentanaClase() {
        this.directorEscena.mostrarVentanaClase();
    }

    public void mostrarVentanaAsignacionAlumno() {
        this.directorEscena.mostrarVentanaAsignacionAlumno();
    }

    public void acercaDe() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Acerca de ...");
        alert.setHeaderText("Sistema de control académico");
        alert.setContentText("Kalum v1.0.0 \nDesarrollado por: Carlos Paz \nGuatemala, C. A. -2020-");
        alert.showAndWait();
    }

    public void salir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Ventana Principal");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir del sistema?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            this.directorEscena.mostrarVentanaPrincipal();
        }
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
}