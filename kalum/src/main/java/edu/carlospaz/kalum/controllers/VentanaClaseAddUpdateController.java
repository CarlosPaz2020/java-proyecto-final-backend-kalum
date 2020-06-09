package edu.carlospaz.kalum.controllers;

import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;

public class VentanaClaseAddUpdateController implements Initializable {
    private App directorEscena;
    private Clase clase;

    @FXML private TextField txtDescripcion;
    @FXML private TextField txtCiclo;
    @FXML private TextField txtCupoMin;
    @FXML private TextField txtCupoMax;
    @FXML private TextField txtSalonId;
    @FXML private TextField txtHorarioId;
    @FXML private TextField txtInstructorId;
    @FXML private TextField txtCarreraId;
    @FXML private TextField txtCantAsig;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        if (txtDescripcion.getText().isEmpty()
            || txtCiclo.getText().isEmpty()
            || txtCupoMin.getText().isEmpty()
            || txtCupoMax.getText().isEmpty()
            || txtSalonId.getText().isEmpty()
            || txtHorarioId.getText().isEmpty()
            || txtInstructorId.getText().isEmpty()
            || txtCarreraId.getText().isEmpty()) {
            
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
                    Salon salon = new Salon();
                    Horario horario = new Horario();
                    Instructor instructor = new Instructor();
                    CarreraTecnica carrera = new CarreraTecnica();
                    
                    clase.setDescripcion(txtDescripcion.getText());
                    clase.setCiclo(Integer.parseInt(txtCiclo.getText()));
                    clase.setCupoMinimo(Integer.parseInt(txtCupoMin.getText()));
                    clase.setCupoMaximo(Integer.parseInt(txtCupoMax.getText()));
                    salon.setSalonId(txtSalonId.getText());
                    clase.setSalon(salon);
                    horario.setHorarioId(txtHorarioId.getText());
                    clase.setHorario(horario);
                    instructor.setInstructorId(txtInstructorId.getText());
                    clase.setInstructor(instructor);
                    carrera.setCodigoCarrera(txtCarreraId.getText());
                    clase.setCarrera(carrera);
                    
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
                Salon salon = new Salon();
                Horario horario = new Horario();
                Instructor instructor = new Instructor();
                CarreraTecnica carrera = new CarreraTecnica();
                
                clase.setClaseId(UUID.randomUUID().toString());
                clase.setDescripcion(txtDescripcion.getText());
                clase.setCiclo(Integer.parseInt(txtCiclo.getText()));
                clase.setCupoMinimo(Integer.parseInt(txtCupoMin.getText()));
                clase.setCupoMaximo(Integer.parseInt(txtCupoMax.getText()));
                salon.setSalonId(txtSalonId.getText());
                clase.setSalon(salon);
                horario.setHorarioId(txtHorarioId.getText());
                clase.setHorario(horario);
                instructor.setInstructorId(txtInstructorId.getText());
                clase.setInstructor(instructor);
                carrera.setCodigoCarrera(txtCarreraId.getText());
                clase.setCarrera(carrera);
                
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

    public void setClase(Clase clase) {
        this.clase = clase;
        this.txtDescripcion.setText(clase.getDescripcion());
        this.txtCiclo.setText(String.valueOf(clase.getCiclo()));
        this.txtCupoMin.setText(String.valueOf(clase.getCupoMinimo()));
        this.txtCupoMax.setText(String.valueOf(clase.getCupoMaximo()));
        this.txtSalonId.setText(clase.getSalon().getSalonId());
        this.txtHorarioId.setText(clase.getHorario().getHorarioId());
        this.txtInstructorId.setText(clase.getInstructor().getInstructorId());
        this.txtCarreraId.setText(clase.getCarrera().getCodigoCarrera());
        this.txtCantAsig.setText(String.valueOf(clase.getCantidadAsignaciones()));
    }
    
    
    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }


}