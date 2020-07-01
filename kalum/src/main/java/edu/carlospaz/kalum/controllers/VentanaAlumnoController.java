package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;


public class VentanaAlumnoController implements Initializable {
    private App directorEscena;
    private ObservableList<Alumno> listaAlumnos;

    @FXML private TableView<Alumno> tblAlumnos;
    @FXML private TableColumn<Alumno, String> colCarne;
    @FXML private TableColumn<Alumno, String> colNoExpediente;
    @FXML private TableColumn<Alumno, String> colApellidos;
    @FXML private TableColumn<Alumno, String> colNombres;
    @FXML private TableColumn<Alumno, String> colEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaAlumnos = FXCollections.observableArrayList(
            (List<Alumno>) Conexion.getInstancia().findAll("Alumno.findAll"));
    this.tblAlumnos.setItems(listaAlumnos);
    this.colCarne.setCellValueFactory(cellCarne
            -> cellCarne.getValue().carne());
    this.colNoExpediente.setCellValueFactory(cellNoExpediente
            -> cellNoExpediente.getValue().noExpediente());
    this.colApellidos.setCellValueFactory(cellApellidos
            -> cellApellidos.getValue().apellidos());
    this.colNombres.setCellValueFactory(cellNombres
            -> cellNombres.getValue().nombres());
    this.colEmail.setCellValueFactory(cellEmail
            -> cellEmail.getValue().email());
    }

    public void modificar() {
        if (this.tblAlumnos.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alumnos");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Alumno alumno = this.tblAlumnos.getSelectionModel().getSelectedItem();
            this.directorEscena.mostrarVentanaAlumnoAddUpdate(alumno);
        }
    }

    public void eliminar() {
        if (this.tblAlumnos.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alumnos");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Alumno alumno = this.tblAlumnos.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Alumnos");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> seleccion = alert.showAndWait();

            if (seleccion.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(alumno);
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Alumnos");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscena.mostrarVentanaAlumno();
            } else {
                this.directorEscena.mostrarVentanaAlumno();
            }
        }
    }

    public void salir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alumnos");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaPrincipal();
        } else {
            this.directorEscena.mostrarVentanaAlumno();
        }
    }

    public void mostrarVentanaPrincipal() {
        this.directorEscena.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaAlumnoAddUpdate() {
        this.directorEscena.mostrarVentanaAlumnoAddUpdate();
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
}