package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Salon;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


public class VentanaSalonController implements Initializable {
    private App directorEscena;
    private ObservableList<Salon> listaSalones;
    
    @FXML private TableView<Salon> tblSalones;
    @FXML private TableColumn<Salon, String> colNombre;
    @FXML private TableColumn<Salon, String> colDescripcion;
    @FXML private TableColumn<Salon, Number> colCapacidad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaSalones = FXCollections.observableArrayList(
                (List<Salon>) Conexion.getInstancia().findAll("Salon.findAll"));
        this.tblSalones.setItems(listaSalones);
        this.colNombre.setCellValueFactory(cellNombre
                -> cellNombre.getValue().nombreSalon());
        this.colDescripcion.setCellValueFactory(cellDescripcion
                -> cellDescripcion.getValue().descripcion());
        this.colCapacidad.setCellValueFactory(cellCapacidad
                -> cellCapacidad.getValue().capacidad());
    }

    public void modificar() {
        if (this.tblSalones.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Salones");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Salon salon = this.tblSalones.getSelectionModel().getSelectedItem();
            this.directorEscena.mostrarVentanaSalonAddUpdate(salon);
        }
    }

    public void eliminar() {
        if (this.tblSalones.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Salones");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Salon salon = this.tblSalones.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Salones");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> seleccion = alert.showAndWait();

            if (seleccion.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(salon);
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Salones");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscena.mostrarVentanaSalon();
            } else {
                this.directorEscena.mostrarVentanaSalon();
            }
        }
    }

    public void salir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Salones");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaPrincipal();
        } else {
            this.directorEscena.mostrarVentanaSalon();
        }
    }

    public void mostrarVentanaPrincipal() {
        this.directorEscena.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaSalonAddUpdate() {
        this.directorEscena.mostrarVentanaSalonAddUpdate();
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
}