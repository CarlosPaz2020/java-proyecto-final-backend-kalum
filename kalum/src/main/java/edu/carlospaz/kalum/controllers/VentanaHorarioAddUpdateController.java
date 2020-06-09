package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.UUID;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Horario;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;

public class VentanaHorarioAddUpdateController implements Initializable {
    private App directorEscena;
    private Horario horario;

    @FXML
    private TextField txtHorarioInicio;

    @FXML
    private TextField txtHorarioFinal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() throws ParseException {
        if (txtHorarioInicio.getText().isEmpty()
            || txtHorarioFinal.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Horarios");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, debe llenar los campos");
            alert.initOwner(null);
            alert.show();
            this.txtHorarioInicio.requestFocus();
        } else {
            if (horario != null) {
                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                alert2.setTitle("Horarios");
                alert2.setHeaderText(null);
                alert2.setContentText("¿Desea actualizar este registro?");
                alert2.initOwner(null);

                Optional<ButtonType> seleccion = alert2.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                    formatoHora.setTimeZone(TimeZone.getTimeZone("CST"));
                    horario.setHorarioInicio(formatoHora.parse(txtHorarioInicio.getText()));
                    horario.setHorarioFinal(formatoHora.parse(txtHorarioFinal.getText()));
                    Conexion.getInstancia().modificar(horario);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Horarios");
                    alert.setHeaderText(null);
                    alert.setContentText("Registro actualizado correctamente");
                    alert.initOwner(null);
                    alert.show();
                    this.directorEscena.mostrarVentanaHorario();
                } else {
                    this.txtHorarioInicio.requestFocus();
                }
                
            } else {
                DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                formatoHora.setTimeZone(TimeZone.getTimeZone("CST"));
                Horario horario = new Horario();
                horario.setHorarioId(UUID.randomUUID().toString());
                horario.setHorarioInicio(formatoHora.parse(txtHorarioInicio.getText()));
                horario.setHorarioFinal(formatoHora.parse(txtHorarioFinal.getText()));

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Horarios");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar este registro?");
                alert.initOwner(null);

                Optional<ButtonType> seleccion = alert.showAndWait();

                if (seleccion.get() == ButtonType.OK) {
                    Conexion.getInstancia().agregar(horario);
                    Alert alert1 = new Alert(AlertType.INFORMATION);
                    alert1.setTitle("Horarios");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Registro guardado correctamente");
                    alert1.initOwner(null);
                    alert1.show();
                    this.directorEscena.mostrarVentanaHorario();
                } else {
                    this.txtHorarioInicio.requestFocus();
                }

            }
        }
    }

    public void cancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Horarios");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaHorario();
        } else {
            this.txtHorarioInicio.requestFocus();
        }
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) throws ParseException {
        this.horario = horario;
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        formatoHora.setTimeZone(TimeZone.getTimeZone("CST"));
        horario.setHorarioInicio(formatoHora.parse(txtHorarioInicio.getText()));
        horario.setHorarioFinal(formatoHora.parse(txtHorarioFinal.getText()));
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }

}