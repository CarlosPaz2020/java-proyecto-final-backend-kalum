package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Clase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;

public class VentanaClaseController implements Initializable {
    private App directorEscena;
    private ObservableList<Clase> listaClases;

    @FXML
    private TableView<Clase> tblClases;

    @FXML
    private TableColumn<Clase, String> colDescripcion;

    @FXML
    private TableColumn<Clase, Number> colCiclo;

    @FXML
    private TableColumn<Clase, Number> colCupoMin;

    @FXML
    private TableColumn<Clase, Number> colCupoMax;

    @FXML
    private TableColumn<Clase, Number> colCantAsig;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaClases = FXCollections.observableArrayList(
            (List<Clase>) Conexion.getInstancia().findAll("Clase.findAll"));
    this.tblClases.setItems(listaClases);
    this.colDescripcion.setCellValueFactory(cellDescripcion
            -> cellDescripcion.getValue().descripcion());
    this.colCiclo.setCellValueFactory(cellCiclo
            -> cellCiclo.getValue().ciclo());
    this.colCupoMin.setCellValueFactory(cellCupoMin
            -> cellCupoMin.getValue().cupoMinimo());
    this.colCupoMax.setCellValueFactory(cellCupoMax
            -> cellCupoMax.getValue().cupoMaximo());
    this.colCantAsig.setCellValueFactory(cellCantAsig
            -> cellCantAsig.getValue().cantidadAsignaciones());
    }

    public void modificar() {
        if (this.tblClases.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Clases");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Clase clase = this.tblClases.getSelectionModel().getSelectedItem();
            this.directorEscena.mostrarVentanaClaseAddUpdate(clase);
        }
    }

    public void eliminar() {
        if (this.tblClases.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Clases");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Clase clase = this.tblClases.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Clases");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> seleccion = alert.showAndWait();

            if (seleccion.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(clase);
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Clases");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscena.mostrarVentanaClase();
            } else {
                this.directorEscena.mostrarVentanaClase();
            }
        }
    }

    public void salir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Clases");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaPrincipal();
        } else {
            this.directorEscena.mostrarVentanaClase();
        }
    }

    public void mostrarVentanaPrincipal() {
        this.directorEscena.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaClaseAddUpdate() {
        this.directorEscena.mostrarVentanaClaseAddUpdate();
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }

    
 
}