package edu.carlospaz.kalum.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Table(name = "asignacion_alumno")
@NamedQueries({@NamedQuery(name = "AsignacionAlumno.findAll", query = "select aa from AsignacionAlumno aa")})
public class AsignacionAlumno implements Serializable {
    private final StringProperty asignacionId;
    private final ObjectProperty<Date> fechaAsignacion;
    private Clase clase;
    private Alumno alumno;

    public AsignacionAlumno() {
        this.asignacionId = new SimpleStringProperty();
        this.fechaAsignacion = new SimpleObjectProperty<Date>();
    }

    public AsignacionAlumno(String asignacionId, Date fechaAsignacion,
                            String claseId, String carne) {
        this.asignacionId = new SimpleStringProperty();
        this.fechaAsignacion = new SimpleObjectProperty<Date>();
    }

    //
    @Id
    @Column(name = "asignacion_id")
    public String getAsignacionId() {
        return this.asignacionId.get();
    }

    public void setAsignacionId(String asignacionId) {
        this.asignacionId.set(asignacionId);
    }

    public StringProperty asignacionId() {
        return this.asignacionId;
    }
    //
    @Column(name = "fecha_asignacion")
    public Date getFechaAsignacion() {
        return this.fechaAsignacion.get();
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion.set(fechaAsignacion);
    }

    public ObjectProperty<Date> fechaAsignacion() {
        return this.fechaAsignacion;
    }

    //Relación con la clase Clase
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clase_id")
    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    //Relación con la clase Alumno
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carne")
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    //
    @Override
    public String toString() {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("CST"));
        return this.getAsignacionId() + " " + formatoFecha.format(this.getFechaAsignacion())
                + " " + this.getClase() + " " + this.getAlumno();
    }

    private static final long serialVersionUID = 1L;

}