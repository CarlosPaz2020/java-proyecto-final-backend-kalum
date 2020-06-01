package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import edu.carlospaz.kalum.App;
import edu.carlospaz.kalum.db.Conexion;
import edu.carlospaz.kalum.models.Horario;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class VentanaHorarioController implements Initializable {
    private App directorEscena;
    private ObservableList<Horario> listaHorarios;

    @FXML
    private TableView<Horario> tblHorarios;

    @FXML
    private TableColumn<Horario, Date> colHorarioInicio;

    @FXML
    private TableColumn<Horario, Date> colHorarioFinal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaHorarios = FXCollections
                .observableArrayList((List<Horario>) Conexion.getInstancia().findAll("Horario.findAll"));
        this.tblHorarios.setItems(listaHorarios);
        this.colHorarioInicio.setCellValueFactory(cellHorarioInicio 
            -> cellHorarioInicio.getValue().horarioInicio());
        this.colHorarioFinal.setCellValueFactory(cellHorarioFinal 
            -> cellHorarioFinal.getValue().horarioFinal());
    }

    public void modificar() throws ParseException {
        if (this.tblHorarios.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Horarios");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Horario horario = this.tblHorarios.getSelectionModel().getSelectedItem();
            this.directorEscena.mostrarVentanaHorarioAddUpdate(horario);
        }
    }

    public void eliminar() {
        if (this.tblHorarios.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Horarios");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un registro");
            alert.initOwner(null);
            alert.show();
        } else {
            Horario horario = this.tblHorarios.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Horarios");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> seleccion = alert.showAndWait();

            if (seleccion.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(horario);
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Horarios");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscena.mostrarVentanaHorario();
            } else {
                this.directorEscena.mostrarVentanaHorario();
            }
        }
    }

    public void salir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Horarios");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir de esta ventana?");
        alert.initOwner(null);

        Optional<ButtonType> seleccion = alert.showAndWait();

        if (seleccion.get() == ButtonType.OK) {
            this.directorEscena.mostrarVentanaPrincipal();
        } else {
            this.directorEscena.mostrarVentanaHorario();
        }
    }

    public void mostrarVentanaPrincipal() {
        this.directorEscena.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaHorarioAddUpdate() {
        this.directorEscena.mostrarVentanaHorarioAddUpdate();
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }
    
    
}