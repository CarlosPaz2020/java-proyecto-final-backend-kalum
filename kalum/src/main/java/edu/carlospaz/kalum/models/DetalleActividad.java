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
@Table(name = "detalle_actividad")
@NamedQueries({@NamedQuery(name = "DetalleActividad.findAll", query = "select da from DetalleActividad da")})
public class DetalleActividad implements Serializable {
    private final StringProperty detalleActividadId;
    private final StringProperty nombreActividad;
    private final StringProperty notaActividad;
    private final ObjectProperty<Date> fechaCreacion;
    private final ObjectProperty<Date> fechaEntrega;
    private final ObjectProperty<Date> fechaPostergacion;
    private final StringProperty estado;
    private Seminario seminario;
    private List<DetalleNota> detalleNotas;

    public DetalleActividad() {
        this.detalleActividadId = new SimpleStringProperty();
        this.nombreActividad = new SimpleStringProperty();
        this.notaActividad = new SimpleStringProperty();
        this.fechaCreacion = new SimpleObjectProperty<Date>();
        this.fechaEntrega = new SimpleObjectProperty<Date>();
        this.fechaPostergacion = new SimpleObjectProperty<Date>();
        this.estado = new SimpleStringProperty();
    }

    public DetalleActividad(String detalleActividadId, String nombreActividad,
                            String notaActividad, Date fechaCreacion, Date fechaEntrega,
                            Date fechaPostergacion, String estado, String seminarioId) {
        this.detalleActividadId = new SimpleStringProperty();
        this.nombreActividad = new SimpleStringProperty();
        this.notaActividad = new SimpleStringProperty();
        this.fechaCreacion = new SimpleObjectProperty<Date>();
        this.fechaEntrega = new SimpleObjectProperty<Date>();
        this.fechaPostergacion = new SimpleObjectProperty<Date>();
        this.estado = new SimpleStringProperty();
    }

    //
    @Id
    @Column(name = "detalle_actividad_id")
    public String getDetalleActividadId() {
        return this.detalleActividadId.get();
    }

    public void setDetalleActividadId(String detalleActividadId) {
        this.detalleActividadId.set(detalleActividadId);
    }

    public StringProperty detalleActividadId() {
        return this.detalleActividadId;
    }
    //
    @Column(name = "nombre_actividad")
    public String getNombreActividad() {
        return this.nombreActividad.get();
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad.set(nombreActividad);
    }

    public StringProperty nombreActividad() {
        return this.nombreActividad;
    }
    //
    @Column(name = "nota_actividad")
    public String getNotaActividad() {
        return this.notaActividad.get();
    }

    public void setNotaActividad(String notaActividad) {
        this.notaActividad.set(notaActividad);
    }

    public StringProperty notaActividad() {
        return this.notaActividad;
    }
    //
    @Temporal(value = TemporalType.DATE)
    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return this.fechaCreacion.get();
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion.set(fechaCreacion);
    }

    public ObjectProperty<Date> fechaCreacion() {
        return this.fechaCreacion;
    }
    //
    @Temporal(value = TemporalType.DATE)
    @Column(name = "fecha_entrega")
    public Date getFechaEntrega() {
        return this.fechaEntrega.get();
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega.set(fechaEntrega);
    }

    public ObjectProperty<Date> fechaEntrega() {
        return this.fechaEntrega;
    }
    //
    @Temporal(value = TemporalType.DATE)
    @Column(name = "fecha_postergacion")
    public Date getFechaPostergacion() {
        return this.fechaPostergacion.get();
    }

    public void setFechaPostergacion(Date fechaPostergacion) {
        this.fechaPostergacion.set(fechaPostergacion);
    }

    public ObjectProperty<Date> fechaPostergacion() {
        return this.fechaPostergacion;
    }
    //
    @Column(name = "estado")
    public String getEstado() {
        return this.estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public StringProperty estado() {
        return this.estado;
    }

    //Relación con la clase Seminario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seminario_id")
    public Seminario getSeminario() {
        return seminario;
    }

    public void setSeminario(Seminario seminario) {
        this.seminario = seminario;
    }

    //Relación con la clase DetalleNota
    @OneToMany(mappedBy = "detalleActividad", fetch = FetchType.EAGER)
    public List<DetalleNota> getDetalleNotas() {
        return detalleNotas;
    }

    public void setDetalleNotas(List<DetalleNota> detalleNotas) {
        this.detalleNotas = detalleNotas;
    }

    //
    @Override
    public String toString() {
        return this.getDetalleActividadId() + " " + this.getNombreActividad()
                + " " + this.getNotaActividad() + " " + this.getFechaCreacion()
                + " " + this.getFechaEntrega() + " " + this.getFechaPostergacion()
                + " " + this.getEstado() + " " + this.getSeminario();
    }

    private static final long serialVersionUID = 1L;

}