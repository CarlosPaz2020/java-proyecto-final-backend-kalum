package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Instructor;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

public class VentanaInstructorAddUpdateController implements Initializable {
    private App directorEscena;
    private Instructor instructor;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextArea txtaComentario;

    @FXML
    private TextField txtEstatus;

    @FXML
    private ImageView imgFoto;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        if (txtApellidos.getText().isEmpty()
            || txtNombres.getText().isEmpty()
            || txtDireccion.getText().isEmpty()
            || txtTelefono.getText().isEmpty()
            || txtaComentario.getText().isEmpty()
            || txtEstatus.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Instructores");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, debe llenar los campos");
            alert.initOwner(null);
            alert.show();
            this.txtApellidos.requestFocus();
        } else {
            if (instructor != null) {
                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                alert2.setTitle("Instructores");
                alert2.setHeaderText(null);
                alert2.setContentText("¿Desea actualizar este registro?");
                alert2.initOwner(null);

                Optional<ButtonType> seleccion = alert2.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    instructor.setApellidos(txtApellidos.getText());
                    instructor.setNombres(txtNombres.getText());
                    instructor.setDireccion(txtDireccion.getText());
                    instructor.setTelefono(txtTelefono.getText());
                    instructor.setComentario(txtaComentario.getText());
                    instructor.setEstatus(txtEstatus.getText());
                    Conexion.getInstancia().modificar(instructor);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Instructores");
                    alert.setHeaderText(null);
                    alert.setContentText("Registro actualizado correctamente");
                    alert.initOwner(null);
                    alert.show();
                    this.directorEscena.mostrarVentanaInstructor();
                } else {
                    this.txtApellidos.requestFocus();
                }
                
            } else {
                Instructor instructor = new Instructor();
                instructor.setInstructorId(UUID.randomUUID().toString());
                instructor.setApellidos(txtApellidos.getText());
                instructor.setNombres(txtNombres.getText());
                instructor.setDireccion(txtDireccion.getText());
                instructor.setTelefono(txtTelefono.getText());
                instructor.setComentario(txtaComentario.getText());
                instructor.setEstatus(txtEstatus.getText());

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Instructores");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar este registro?");
                alert.initOwner(null);

                Optional<ButtonType> seleccion = alert.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    Conexion.getInstancia().agregar(instructor);
                    Alert alert1 = new Alert(AlertType.INFORMATION);
                    alert1.setTitle("Instructores");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Registro guardado correctamente");
                    alert1.initOwner(null);
                    alert1.show();
                    this.directorEscena.mostrarVentanaInstructor();
                } else {
                    this.txtApellidos.requestFocus();
                }

            }
        }
    }

    public void cancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Instructores");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaInstructor();
        } else {
            this.txtApellidos.requestFocus();
        }
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        this.txtApellidos.setText(instructor.getApellidos());
        this.txtNombres.setText(instructor.getNombres());
        this.txtDireccion.setText(instructor.getDireccion());
        this.txtTelefono.setText(instructor.getTelefono());
        this.txtaComentario.setText(instructor.getComentario());
        this.txtEstatus.setText(instructor.getEstatus());
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }

}