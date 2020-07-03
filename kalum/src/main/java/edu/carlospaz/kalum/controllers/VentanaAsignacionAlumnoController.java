package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.AsignacionAlumno;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.beans.property.ReadOnlyStringWrapper;


public class VentanaAsignacionAlumnoController implements Initializable {
    private App directorEscena;
    private ObservableList<AsignacionAlumno> listaAsignaciones;

    @FXML private TableView<AsignacionAlumno> tblAsignaciones;
    @FXML private TableColumn<AsignacionAlumno,String> colFechaAsignacion;
    @FXML private TableColumn<AsignacionAlumno,String> colClase;
    @FXML private TableColumn<AsignacionAlumno,String> colCarne;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("CST"));
        listaAsignaciones = FXCollections
                .observableArrayList((List<AsignacionAlumno>) Conexion.getInstancia().findAll("AsignacionAlumno.findAll"));
        this.tblAsignaciones.setItems(listaAsignaciones);
        
        this.colFechaAsignacion.setCellValueFactory(cellFechaAsignacion
            -> new ReadOnlyStringWrapper(
                formatoFecha.format(cellFechaAsignacion.getValue().getFechaAsignacion()).toString()));
        this.colClase.setCellValueFactory(cellDescripcionClase
                -> cellDescripcionClase.getValue().getClase().descripcion());
        this.colCarne.setCellValueFactory(cellCarneAlumno
                -> new ReadOnlyStringWrapper(
                    cellCarneAlumno.getValue().getAlumno().getCarne() + " - " +
                    cellCarneAlumno.getValue().getAlumno().getApellidos() + ", " + 
                    cellCarneAlumno.getValue().getAlumno().getNombres()));        
    }

    public void modificar() throws ParseException {
        if (this.tblAsignaciones.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Asignaciones");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            AsignacionAlumno asignacion = this.tblAsignaciones.getSelectionModel().getSelectedItem();
            this.directorEscena.mostrarVentanaAsignacionAlumnoAddUpdate(asignacion);
        }
    }

    public void eliminar() {
        if (this.tblAsignaciones.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Asignaciones");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            AsignacionAlumno asignacion = this.tblAsignaciones.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Asignaciones");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> seleccion = alert.showAndWait();

            if (seleccion.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(asignacion);
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Asignaciones");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscena.mostrarVentanaAsignacionAlumno();
            } else {
                this.directorEscena.mostrarVentanaAsignacionAlumno();
            }
        }
    }

    public void salir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Asignaciones");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaPrincipal();
        } else {
            this.directorEscena.mostrarVentanaAsignacionAlumno();
        }
    }

    public void mostrarVentanaPrincipal() {
        this.directorEscena.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaAsignacionAlumnoAddUpdate() {
        this.directorEscena.mostrarVentanaAsignacionAlumnoAddUpdate();
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
}