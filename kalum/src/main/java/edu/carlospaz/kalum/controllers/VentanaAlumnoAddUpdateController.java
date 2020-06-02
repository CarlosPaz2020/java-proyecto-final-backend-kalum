package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Alumno;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

public class VentanaAlumnoAddUpdateController implements Initializable {
    private App directorEscena;
    private Alumno alumno;

    @FXML
    private TextField txtCarne;

    @FXML
    private TextField txtNoExpediente;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtEmail;

    @FXML
    private ImageView imgFoto;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        if (txtCarne.getText().isEmpty()
            || txtNoExpediente.getText().isEmpty()
            || txtApellidos.getText().isEmpty()
            || txtNombres.getText().isEmpty()
            || txtEmail.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alumnos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, debe llenar los campos");
            alert.initOwner(null);
            alert.show();
            this.txtApellidos.requestFocus();
        } else {
            if (alumno != null) {
                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                alert2.setTitle("Alumnos");
                alert2.setHeaderText(null);
                alert2.setContentText("¿Desea actualizar este registro?");
                alert2.initOwner(null);

                Optional<ButtonType> seleccion = alert2.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    alumno.setCarne(txtCarne.getText());
                    alumno.setNoExpediente(txtNoExpediente.getText());
                    alumno.setApellidos(txtApellidos.getText());
                    alumno.setNombres(txtNombres.getText());
                    alumno.setEmail(txtEmail.getText());
                    Conexion.getInstancia().modificar(alumno);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Alumnos");
                    alert.setHeaderText(null);
                    alert.setContentText("Registro actualizado correctamente");
                    alert.initOwner(null);
                    alert.show();
                    this.directorEscena.mostrarVentanaAlumno();
                } else {
                    this.txtCarne.requestFocus();
                }
                
            } else {
                Alumno alumno = new Alumno();
                alumno.setCarne(txtCarne.getText());
                alumno.setNoExpediente(txtNoExpediente.getText());
                alumno.setApellidos(txtApellidos.getText());
                alumno.setNombres(txtNombres.getText());
                alumno.setEmail(txtEmail.getText());

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Alumnos");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar este registro?");
                alert.initOwner(null);

                Optional<ButtonType> seleccion = alert.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    Conexion.getInstancia().agregar(alumno);
                    Alert alert1 = new Alert(AlertType.INFORMATION);
                    alert1.setTitle("Alumnos");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Registro guardado correctamente");
                    alert1.initOwner(null);
                    alert1.show();
                    this.directorEscena.mostrarVentanaAlumno();
                } else {
                    this.txtApellidos.requestFocus();
                }

            }
        }
    }

    public void cancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alumnos");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaAlumno();
        } else {
            this.txtApellidos.requestFocus();
        }
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
        this.txtCarne.setText(alumno.getCarne());
        this.txtNoExpediente.setText(alumno.getNoExpediente());
        this.txtApellidos.setText(alumno.getApellidos());
        this.txtNombres.setText(alumno.getNombres());
        this.txtEmail.setText(alumno.getEmail());
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }

}