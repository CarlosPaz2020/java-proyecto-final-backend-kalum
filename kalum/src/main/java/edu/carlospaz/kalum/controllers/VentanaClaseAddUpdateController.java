package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.CarreraTecnica;
import edu.carlospaz.kalum.models.Clase;
import edu.carlospaz.kalum.models.Horario;
import edu.carlospaz.kalum.models.Instructor;
import edu.carlospaz.kalum.models.Salon;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class VentanaClaseAddUpdateController implements Initializable {
    private App directorEscena;
    private Clase clase;
    private ObservableList<Salon> salones;
    private ObservableList<Horario> horarios;
    private ObservableList<Instructor> instructores;
    private ObservableList<CarreraTecnica> carrerasTecnicas;

    @FXML private TextField txtDescripcion;
    @FXML private TextField txtCiclo;
    @FXML private TextField txtCupoMin;
    @FXML private TextField txtCupoMax;
    @FXML private ComboBox<Salon> cmbSalon;
    @FXML private ComboBox<Horario> cmbHorario;
    @FXML private ComboBox<Instructor> cmbInstructor;
    @FXML private ComboBox<CarreraTecnica> cmbCarrera;
    @FXML private TextField txtCantAsig;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salones = FXCollections.observableArrayList((List<Salon>)
            Conexion.getInstancia().findAll("Salon.findAll"));
        this.cmbSalon.setItems(salones);

        horarios = FXCollections.observableArrayList((List<Horario>)
            Conexion.getInstancia().findAll("Horario.findAll"));
        this.cmbHorario.setItems(horarios);

        instructores = FXCollections.observableArrayList((List<Instructor>)
            Conexion.getInstancia().findAll("Instructor.findAll"));
        this.cmbInstructor.setItems(instructores);

        carrerasTecnicas = FXCollections.observableArrayList((List<CarreraTecnica>)
            Conexion.getInstancia().findAll("CarreraTecnica.findAll"));
        
        this.cmbCarrera.setItems(carrerasTecnicas);
    }

    public void guardar() {
        if (txtDescripcion.getText().isEmpty()
            || txtCiclo.getText().isEmpty()
            || txtCupoMin.getText().isEmpty()
            || txtCupoMax.getText().isEmpty()
            || cmbSalon.getSelectionModel().getSelectedItem() == null
            || cmbHorario.getSelectionModel().getSelectedItem() == null
            || cmbInstructor.getSelectionModel().getSelectedItem() == null
            || cmbCarrera.getSelectionModel().getSelectedItem() == null) {
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Clases");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, debe llenar los campos");
            alert.initOwner(null);
            alert.show();
            this.txtDescripcion.requestFocus();
        } else {
            if (clase != null) {
                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                alert2.setTitle("Clases");
                alert2.setHeaderText(null);
                alert2.setContentText("¿Desea actualizar este registro?");
                alert2.initOwner(null);

                Optional<ButtonType> seleccion = alert2.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    
                    clase.setDescripcion(txtDescripcion.getText());
                    clase.setCiclo(Integer.parseInt(txtCiclo.getText()));
                    clase.setCupoMinimo(Integer.parseInt(txtCupoMin.getText()));
                    clase.setCupoMaximo(Integer.parseInt(txtCupoMax.getText()));
                    
                    clase.setSalon(this.cmbSalon.getSelectionModel().getSelectedItem());
                    clase.setHorario(this.cmbHorario.getSelectionModel().getSelectedItem());
                    clase.setInstructor(this.cmbInstructor.getSelectionModel().getSelectedItem());
                    clase.setCarrera(this.cmbCarrera.getSelectionModel().getSelectedItem());
                    
                    Conexion.getInstancia().modificar(clase);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Clases");
                    alert.setHeaderText(null);
                    alert.setContentText("Registro actualizado correctamente");
                    alert.initOwner(null);
                    alert.show();
                    this.directorEscena.mostrarVentanaClase();
                } else {
                    this.txtDescripcion.requestFocus();
                }
                
            } else {
                Clase clase = new Clase();
                                
                clase.setClaseId(UUID.randomUUID().toString());
                clase.setDescripcion(txtDescripcion.getText());
                clase.setCiclo(Integer.parseInt(txtCiclo.getText()));
                clase.setCupoMinimo(Integer.parseInt(txtCupoMin.getText()));
                clase.setCupoMaximo(Integer.parseInt(txtCupoMax.getText()));
                
                clase.setSalon(this.cmbSalon.getSelectionModel().getSelectedItem());
                clase.setHorario(this.cmbHorario.getSelectionModel().getSelectedItem());
                clase.setInstructor(this.cmbInstructor.getSelectionModel().getSelectedItem());
                clase.setCarrera(this.cmbCarrera.getSelectionModel().getSelectedItem());
                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Clases");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar este registro?");
                alert.initOwner(null);

                Optional<ButtonType> seleccion = alert.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    Conexion.getInstancia().agregar(clase);
                    Alert alert1 = new Alert(AlertType.INFORMATION);
                    alert1.setTitle("Clases");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Registro guardado correctamente");
                    alert1.initOwner(null);
                    alert1.show();
                    this.directorEscena.mostrarVentanaClase();
                } else {
                    this.txtDescripcion.requestFocus();
                }

            }
        }
    }

    public void cancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Clases");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaClase();
        } else {
            this.txtDescripcion.requestFocus();
        }
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) throws ParseException {
        this.clase = clase;
        this.txtDescripcion.setText(clase.getDescripcion());
        this.txtCiclo.setText(String.valueOf(clase.getCiclo()));
        this.txtCupoMin.setText(String.valueOf(clase.getCupoMinimo()));
        this.txtCupoMax.setText(String.valueOf(clase.getCupoMaximo()));
        this.cmbSalon.setValue(clase.getSalon());
        this.cmbHorario.setValue(clase.getHorario());
        this.cmbInstructor.setValue(clase.getInstructor());
        this.cmbCarrera.setValue(clase.getCarrera());
        this.txtCantAsig.setText(String.valueOf(clase.getCantidadAsignaciones()));
    }
    
    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }


}