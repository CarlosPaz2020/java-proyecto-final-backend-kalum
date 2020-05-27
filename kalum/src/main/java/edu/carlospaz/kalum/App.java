package edu.carlospaz.kalum;

import java.io.IOException;
import java.io.InputStream;
import edu.carlospaz.kalum.controllers.VentanaCarreraTecnicaAddUpdateController;
import edu.carlospaz.kalum.controllers.VentanaCarreraTecnicaController;
import edu.carlospaz.kalum.controllers.VentanaPrincipalController;
import edu.carlospaz.kalum.models.CarreraTecnica;
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
