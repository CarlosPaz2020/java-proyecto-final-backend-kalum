package edu.carlospaz.kalum.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "carrera_tecnica")
@NamedQueries({@NamedQuery(name = "CarreraTecnica.findAll", query = "select ct from CarreraTecnica ct")})
public class CarreraTecnica implements Serializable {
    private final StringProperty codigoCarrera;
    private final StringProperty nombre;
    private List<Modulo> modulos;
    private List<Clase> clases;

    public CarreraTecnica() {
        this.codigoCarrera = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
    }

    public CarreraTecnica(String codigoCarrera, String nombre) {
        this.codigoCarrera = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
    }

    //
    @Id
    @Column(name = "codigo_carrera")
    public String getCodigoCarrera() {
        return this.codigoCarrera.get();
    }

    public void setCodigoCarrera(String codigoCarrera) {
        this.codigoCarrera.set(codigoCarrera);
    }

    public StringProperty codigoCarrera() {
        return this.codigoCarrera;
    }

    //
    @Column(name = "nombre")
    public String getNombre() {
        return this.nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombre() {
        return this.nombre;
    }

    //Relación con la clase Modulo
    @OneToMany(mappedBy = "carrera", fetch = FetchType.EAGER)
    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    //Relación con la clase Clase
    @OneToMany(mappedBy = "carrera", fetch = FetchType.EAGER)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    //
    @Override
    public String toString() {
        return this.getCodigoCarrera() + " " + this.getNombre();
    }

    private static final long serialVersionUID = 1L;

}