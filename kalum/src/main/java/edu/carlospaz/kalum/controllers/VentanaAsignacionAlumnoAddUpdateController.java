package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.UUID;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Alumno;
import edu.carlospaz.kalum.models.AsignacionAlumno;
import edu.carlospaz.kalum.models.Clase;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


public class VentanaAsignacionAlumnoAddUpdateController implements Initializable {
    private App directorEscena;
    private AsignacionAlumno asignacion;
    private ObservableList<Clase> clases;
    private ObservableList<Alumno> alumnos;

    @FXML private TextField txtFechaAsignacion;
    @FXML private ComboBox<Clase> cmbClase;
    @FXML private ComboBox<Alumno> cmbCarne;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
        Calendar cal = Calendar.getInstance();
        this.txtFechaAsignacion.setText(dateFormat.format(cal.getTime()));

        clases = FXCollections.observableArrayList((List<Clase>) Conexion.getInstancia().findAll("Clase.findAll"));
        this.cmbClase.setItems(clases);

        alumnos = FXCollections.observableArrayList((List<Alumno>) Conexion.getInstancia().findAll("Alumno.findAll"));
        this.cmbCarne.setItems(alumnos);
    }

    public void guardar() throws ParseException {
        if (cmbClase.getSelectionModel().getSelectedItem() == null
            || cmbCarne.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Asignaciones");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, debe llenar los campos");
            alert.initOwner(null);
            alert.show();
            this.cmbClase.requestFocus();
        } else {
            if (asignacion != null) {
                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                alert2.setTitle("Asignaciones");
                alert2.setHeaderText(null);
                alert2.setContentText("¿Desea actualizar este registro?");
                alert2.initOwner(null);

                Optional<ButtonType> seleccion = alert2.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    formatoFecha.setTimeZone(TimeZone.getTimeZone("CST"));
                    asignacion.setClase(this.cmbClase.getSelectionModel().getSelectedItem());
                    asignacion.setAlumno(this.cmbCarne.getSelectionModel().getSelectedItem());
                    //asignacion.setFechaAsignacion(formatoFecha.parse(this.txtFechaAsignacion.getText()));
                    
                    Conexion.getInstancia().modificar(asignacion);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Asignaciones");
                    alert.setHeaderText(null);
                    alert.setContentText("Registro actualizado correctamente");
                    alert.initOwner(null);
                    alert.show();
                    this.directorEscena.mostrarVentanaAsignacionAlumno();
                } else {
                    this.cmbClase.requestFocus();
                }

            } else {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
                Calendar cal = Calendar.getInstance();
                AsignacionAlumno asignacion = new AsignacionAlumno();
                asignacion.setAsignacionId(UUID.randomUUID().toString());
                asignacion.setFechaAsignacion(dateFormat.parse(dateFormat.format(cal.getTime())));
                System.out.println("Fecha corriente antes de guardar: " + dateFormat.format(asignacion.getFechaAsignacion()));

                asignacion.setClase(this.cmbClase.getSelectionModel().getSelectedItem());
                asignacion.setAlumno(this.cmbCarne.getSelectionModel().getSelectedItem());

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Asignaciones");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar este registro?");
                alert.initOwner(null);

                Optional<ButtonType> seleccion = alert.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    Conexion.getInstancia().agregar(asignacion);
                    System.out.println("Fecha corriente después de guardar: " + dateFormat.format(asignacion.getFechaAsignacion()));
                    
                    Alert alert1 = new Alert(AlertType.INFORMATION);
                    alert1.setTitle("Asignaciones");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Registro guardado correctamente");
                    alert1.initOwner(null);
                    alert1.show();
                    this.directorEscena.mostrarVentanaAsignacionAlumno();
                } else {
                    this.cmbClase.requestFocus();
                }
            }
        }
    }

    public void cancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Asignaciones");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaAsignacionAlumno();
        } else {
            this.cmbClase.requestFocus();
        }
    }

    public AsignacionAlumno getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(AsignacionAlumno asignacion) throws ParseException {
        this.asignacion = asignacion;
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("CST"));
        this.txtFechaAsignacion.setText(formatoFecha.format(asignacion.getFechaAsignacion()));
        System.out.println("setAsignacion: " + formatoFecha.format(asignacion.getFechaAsignacion()));
        this.cmbClase.setValue(asignacion.getClase());
        this.cmbCarne.setValue(asignacion.getAlumno());
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
}