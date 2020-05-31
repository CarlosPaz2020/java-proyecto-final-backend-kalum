package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Instructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;

public class VentanaInstructorController implements Initializable {
    private App directorEscena;
    private ObservableList<Instructor> listaInstructores;

    @FXML
    private TableView<Instructor> tblInstructores;

    @FXML
    private TableColumn<Instructor, String> colApellidos;

    @FXML
    private TableColumn<Instructor, String> colNombres;

    @FXML
    private TableColumn<Instructor, String> colDireccion;

    @FXML
    private TableColumn<Instructor, String> colTelefono;

    @FXML
    private TableColumn<Instructor, String> colEstatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaInstructores = FXCollections.observableArrayList(
            (List<Instructor>) Conexion.getInstancia().findAll("Instructor.findAll"));
    this.tblInstructores.setItems(listaInstructores);
    this.colApellidos.setCellValueFactory(cellApellidos
            -> cellApellidos.getValue().apellidos());
    this.colNombres.setCellValueFactory(cellNombres
            -> cellNombres.getValue().nombres());
    this.colDireccion.setCellValueFactory(cellDireccion
            -> cellDireccion.getValue().direccion());
    this.colTelefono.setCellValueFactory(cellTelefono
            -> cellTelefono.getValue().telefono());
    this.colEstatus.setCellValueFactory(cellEstatus
            -> cellEstatus.getValue().etatus());
    }

    public void modificar() {
        if (this.tblInstructores.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Instructores");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Instructor instructor = this.tblInstructores.getSelectionModel().getSelectedItem();
            this.directorEscena.mostrarVentanaInstructorAddUpdate(instructor);
        }
    }

    public void eliminar() {
        if (this.tblInstructores.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Instructores");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Instructor instructor = this.tblInstructores.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Instructores");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> seleccion = alert.showAndWait();

            if (seleccion.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(instructor);
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Instructores");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscena.mostrarVentanaInstructor();
            } else {
                this.directorEscena.mostrarVentanaInstructor();
            }
        }
    }

    public void salir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Instructores");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaPrincipal();
        } else {
            this.directorEscena.mostrarVentanaInstructor();
        }
    }

    public void mostrarVentanaPrincipal() {
        this.directorEscena.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaInstructorAddUpdate() {
        this.directorEscena.mostrarVentanaInstructorAddUpdate();
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }

    
 
}