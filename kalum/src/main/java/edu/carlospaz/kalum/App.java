package edu.carlospaz.kalum;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import edu.carlospaz.kalum.controllers.VentanaAlumnoAddUpdateController;
import edu.carlospaz.kalum.controllers.VentanaAlumnoController;
import edu.carlospaz.kalum.controllers.VentanaCarreraTecnicaAddUpdateController;
import edu.carlospaz.kalum.controllers.VentanaCarreraTecnicaController;
import edu.carlospaz.kalum.controllers.VentanaHorarioAddUpdateController;
import edu.carlospaz.kalum.controllers.VentanaHorarioController;
import edu.carlospaz.kalum.controllers.VentanaInstructorAddUpdateController;
import edu.carlospaz.kalum.controllers.VentanaInstructorController;
import edu.carlospaz.kalum.controllers.VentanaPrincipalController;
import edu.carlospaz.kalum.controllers.VentanaSalonAddUpdateController;
import edu.carlospaz.kalum.controllers.VentanaSalonController;
import edu.carlospaz.kalum.models.Alumno;
import edu.carlospaz.kalum.models.CarreraTecnica;
import edu.carlospaz.kalum.models.Horario;
import edu.carlospaz.kalum.models.Instructor;
import edu.carlospaz.kalum.models.Salon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;

public class App extends Application {
    private final String PAQUETE_VISTAS = "/edu/carlospaz/kalum/views/";
    private Stage escenarioPrincipal;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Kalum v1.0.0");
        mostrarVentanaPrincipal();
        this.escenarioPrincipal.show();
    }

    public void mostrarVentanaPrincipal() {
        try {
            VentanaPrincipalController ventanaPrincipalView 
                    = (VentanaPrincipalController) cambiarEscena(
                    "VentanaPrincipalView.fxml", 650, 400);
            ventanaPrincipalView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaCarrera() {
        try {
            VentanaCarreraTecnicaController ventanaCarreraView 
                    = (VentanaCarreraTecnicaController) cambiarEscena(
                    "VentanaCarreraTecnicaView.fxml", 650, 400);
            ventanaCarreraView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaCarreraTecnicaAddUpdate() {
        try {
            VentanaCarreraTecnicaAddUpdateController ventanaCarreraAddUpdateView 
                    = (VentanaCarreraTecnicaAddUpdateController) cambiarEscena(
                    "VentanaCarreraTecnicaAddUpdateView.fxml", 650, 400);
            ventanaCarreraAddUpdateView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaCarreraTecnicaAddUpdate(CarreraTecnica carreraTecnica) {
        try {
            VentanaCarreraTecnicaAddUpdateController ventanaCarreraAddUpdateView 
                    = (VentanaCarreraTecnicaAddUpdateController) cambiarEscena(
                    "VentanaCarreraTecnicaAddUpdateView.fxml", 650, 400);
            ventanaCarreraAddUpdateView.setDirectorEscena(this);
            ventanaCarreraAddUpdateView.setCarreraTecnica(carreraTecnica);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaInstructor() {
        try {
            VentanaInstructorController ventanaInstructorView 
                    = (VentanaInstructorController) cambiarEscena(
                    "VentanaInstructorView.fxml", 650, 400);
            ventanaInstructorView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaInstructorAddUpdate() {
        try {
            VentanaInstructorAddUpdateController ventanaInstructorAddUpdateView 
                    = (VentanaInstructorAddUpdateController) cambiarEscena(
                    "VentanaInstructorAddUpdateView.fxml", 650, 400);
            ventanaInstructorAddUpdateView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaInstructorAddUpdate(Instructor instructor) {
        try {
            VentanaInstructorAddUpdateController ventanaInstructorAddUpdateView 
                    = (VentanaInstructorAddUpdateController) cambiarEscena(
                    "VentanaInstructorAddUpdateView.fxml", 650, 400);
            ventanaInstructorAddUpdateView.setDirectorEscena(this);
            ventanaInstructorAddUpdateView.setInstructor(instructor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaSalon() {
        try {
            VentanaSalonController ventanaSalonView 
                    = (VentanaSalonController) cambiarEscena(
                    "VentanaSalonView.fxml", 650, 400);
            ventanaSalonView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaSalonAddUpdate() {
        try {
            VentanaSalonAddUpdateController ventanaSalonAddUpdateView 
                    = (VentanaSalonAddUpdateController) cambiarEscena(
                    "VentanaSalonAddUpdateView.fxml", 650, 400);
            ventanaSalonAddUpdateView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaSalonAddUpdate(Salon salon) {
        try {
            VentanaSalonAddUpdateController ventanaSalonAddUpdateView 
                    = (VentanaSalonAddUpdateController) cambiarEscena(
                    "VentanaSalonAddUpdateView.fxml", 650, 400);
            ventanaSalonAddUpdateView.setDirectorEscena(this);
            ventanaSalonAddUpdateView.setSalon(salon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaHorario() {
        try {
            VentanaHorarioController ventanaHorarioView 
                    = (VentanaHorarioController) cambiarEscena(
                    "VentanaHorarioView.fxml", 650, 400);
            ventanaHorarioView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaHorarioAddUpdate() {
        try {
            VentanaHorarioAddUpdateController ventanaHorarioAddUpdateView 
                    = (VentanaHorarioAddUpdateController) cambiarEscena(
                    "VentanaHorarioAddUpdateView.fxml", 650, 400);
            ventanaHorarioAddUpdateView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaHorarioAddUpdate(Horario horario) throws ParseException {
        try {
            VentanaHorarioAddUpdateController ventanaHorarioAddUpdateView
                    = (VentanaHorarioAddUpdateController) cambiarEscena(
                    "VentanaHorarioAddUpdateView.fxml", 650, 400);
            ventanaHorarioAddUpdateView.setDirectorEscena(this);
            ventanaHorarioAddUpdateView.setHorario(horario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaAlumno() {
        try {
            VentanaAlumnoController ventanaAlumnoView 
                    = (VentanaAlumnoController) cambiarEscena(
                    "VentanaAlumnoView.fxml", 650, 400);
            ventanaAlumnoView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaAlumnoAddUpdate() {
        try {
            VentanaAlumnoAddUpdateController ventanaAlumnoAddUpdateView 
                    = (VentanaAlumnoAddUpdateController) cambiarEscena(
                    "VentanaAlumnoAddUpdateView.fxml", 650, 400);
            ventanaAlumnoAddUpdateView.setDirectorEscena(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaAlumnoAddUpdate(Alumno alumno) {
        try {
            VentanaAlumnoAddUpdateController ventanaAlumnoAddUpdateView
                    = (VentanaAlumnoAddUpdateController) cambiarEscena(
                    "VentanaAlumnoAddUpdateView.fxml", 650, 400);
            ventanaAlumnoAddUpdateView.setDirectorEscena(this);
            ventanaAlumnoAddUpdateView.setAlumno(alumno);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Initializable cambiarEscena(String escena, int ancho, int alto) throws IOException {
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivoFXML = App.class.getResourceAsStream(PAQUETE_VISTAS + escena);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(App.class.getResource(PAQUETE_VISTAS + escena));
        Scene miEscena = new Scene((AnchorPane) cargadorFXML.load(archivoFXML), ancho, alto);
        this.escenarioPrincipal.setScene(miEscena);
        this.escenarioPrincipal.sizeToScene();
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }
}
