package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import javafx.fxml.Initializable;

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

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
    
    
}