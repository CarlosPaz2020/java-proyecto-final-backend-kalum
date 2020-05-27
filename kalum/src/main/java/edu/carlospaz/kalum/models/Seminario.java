package edu.carlospaz.kalum.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "seminario")
@NamedQueries({@NamedQuery(name = "Seminario.findAll", query = "select s from Seminario s")})
public class Seminario implements Serializable {
    private final StringProperty seminarioId;
    private final StringProperty nombreSeminario;
    private final ObjectProperty<Date> fechaInicio;
    private final ObjectProperty<Date> fechaFin;
    private Modulo modulo;
    private List<DetalleActividad> actividades;

    public Seminario() {
        this.seminarioId = new SimpleStringProperty();
        this.nombreSeminario = new SimpleStringProperty();
        this.fechaInicio = new SimpleObjectProperty<Date>();
        this.fechaFin = new SimpleObjectProperty<Date>();
    }

    public Seminario(String seminarioId, String nombreSeminario,
                     Date fechaInicio, Date fechaFin, String moduloId) {
        this.seminarioId = new SimpleStringProperty();
        this.nombreSeminario = new SimpleStringProperty();
        this.fechaInicio = new SimpleObjectProperty<Date>();
        this.fechaFin = new SimpleObjectProperty<Date>();
    }

    //
    @Id
    @Column(name = "seminario_id")
    public String getSeminarioId() {
        return this.seminarioId.get();
    }

    public void setSeminarioId(String seminarioId) {
        this.seminarioId.set(seminarioId);
    }

    public StringProperty seminarioId() {
        return this.seminarioId;
    }

    //
    @Column(name = "nombre_seminario")
    public String getNombreSeminario() {
        return this.nombreSeminario.get();
    }

    public void setNombreSeminario(String nombreSeminario) {
        this.nombreSeminario.set(nombreSeminario);
    }

    public StringProperty nombreSeminario() {
        return this.nombreSeminario;
    }

    //
    @Temporal(value = TemporalType.DATE)
    @Column(name = "fecha_inicio")
    public Date getFechaInicio() {
        return this.fechaInicio.get();
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio.set(fechaInicio);
    }

    public ObjectProperty<Date> fechaInicio() {
        return this.fechaInicio();
    }

    //
    @Temporal(value = TemporalType.DATE)
    @Column(name = "fecha_fin")
    public Date getFechaFin() {
        return this.fechaFin.get();
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin.set(fechaFin);
    }

    public ObjectProperty<Date> fechaFin() {
        return this.fechaFin();
    }

    //Relación con la clase Modulo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modulo_id")
    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    //Relación con la clase DetalleActividades
    @OneToMany(mappedBy = "seminario", fetch = FetchType.EAGER)
    public List<DetalleActividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<DetalleActividad> actividades) {
        this.actividades = actividades;
    }

    //
    @Override
    public String toString() {
        return this.getSeminarioId() + " " + this.getNombreSeminario()
                + " " + this.getFechaInicio() + " " + this.getFechaFin() + " " + this.getModulo();
    }

    private static final long serialVersionUID = 1L;

}