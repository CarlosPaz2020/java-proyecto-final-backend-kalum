package edu.carlospaz.kalum.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.beans.property.ReadOnlyStringWrapper;

public class VentanaHorarioController implements Initializable {
    private App directorEscena;
    private ObservableList<Horario> listaHorarios;

    @FXML private TableView<Horario> tblHorarios;
    @FXML private TableColumn<Horario,String> colHorarioInicio;
    @FXML private TableColumn<Horario,String> colHorarioFinal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        listaHorarios = FXCollections
                .observableArrayList((List<Horario>) Conexion.getInstancia().findAll("Horario.findAll"));
        this.tblHorarios.setItems(listaHorarios);
        
        this.colHorarioInicio.setCellValueFactory(cellHorarioInicio
            -> new ReadOnlyStringWrapper(
                formatoHora.format(cellHorarioInicio.getValue().getHorarioInicio()).toString()));
        this.colHorarioFinal.setCellValueFactory(cellHorarioFinal
                -> new ReadOnlyStringWrapper(
                    formatoHora.format(cellHorarioFinal.getValue().getHorarioFinal()).toString()));
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
            System.out.println(horario.getHorarioInicio() + "-" + horario.getHorarioFinal());
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