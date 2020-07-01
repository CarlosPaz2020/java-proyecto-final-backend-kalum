package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.CarreraTecnica;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


public class VentanaCarreraTecnicaController implements Initializable {
    private App directorEscena;
    private ObservableList<CarreraTecnica> listaCarreras;
    
    @FXML private TableView<CarreraTecnica> tblCarreras;
    @FXML private TableColumn<CarreraTecnica, String> colId;
    @FXML private TableColumn<CarreraTecnica, String> colNombre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaCarreras = FXCollections.observableArrayList(
                (List<CarreraTecnica>) Conexion.getInstancia().findAll("CarreraTecnica.findAll"));
        this.tblCarreras.setItems(listaCarreras);
        this.colId.setCellValueFactory(cellId
                -> cellId.getValue().codigoCarrera());
        this.colNombre.setCellValueFactory(cellNombre
                -> cellNombre.getValue().nombre());
    }

    public void modificar() {
        if (this.tblCarreras.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Carreras Técnicas");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            CarreraTecnica carreraTecnica = this.tblCarreras.getSelectionModel().getSelectedItem();
            this.directorEscena.mostrarVentanaCarreraTecnicaAddUpdate(carreraTecnica);
        }
    }

    public void eliminar() {
        if (this.tblCarreras.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Carreras Técnicas");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            CarreraTecnica carreraTecnica = this.tblCarreras.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Carreras Técnicas");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> seleccion = alert.showAndWait();

            if (seleccion.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(carreraTecnica);
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Carreras Técnicas");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscena.mostrarVentanaCarrera();
            } else {
                this.directorEscena.mostrarVentanaCarrera();
            }
        }
    }

    public void salir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Carreras Técnicas");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaPrincipal();
        } else {
            this.directorEscena.mostrarVentanaCarrera();
        }
    }

    public void mostrarVentanaPrincipal() {
        this.directorEscena.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaCarreraTecnicaAddUpdate() {
        this.directorEscena.mostrarVentanaCarreraTecnicaAddUpdate();
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
}