package edu.carlospaz.kalum.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Table(name = "detalle_nota")
@NamedQueries({@NamedQuery(name = "DetalleNota.findAll", query = "select dn from DetalleNota dn")})
public class DetalleNota implements Serializable {
    private final StringProperty detalleNotaId;
    private final IntegerProperty valorNota;
    private DetalleActividad detalleActividad;
    private final StringProperty carne;

    public DetalleNota() {
        this.detalleNotaId = new SimpleStringProperty();
        this.valorNota = new SimpleIntegerProperty();
        this.carne = new SimpleStringProperty();
    }

    public DetalleNota(String detalleNotaId, int valorNota,
                       String detalleActividadId, String carne) {
        this.detalleNotaId = new SimpleStringProperty();
        this.valorNota = new SimpleIntegerProperty();
        this.carne = new SimpleStringProperty();
    }

    //
    @Id
    @Column(name = "detalle_nota_id")
    public String getDetalleNotaId() {
        return this.detalleNotaId.get();
    }

    public void setDetalleNotaId(String detalleNotaId) {
        this.detalleNotaId.set(detalleNotaId);
    }

    public StringProperty detalleNotaId() {
        return this.detalleNotaId;
    }
    //
    @Column(name = "valor_nota")
    public int getValorNota() {
        return this.valorNota.get();
    }

    public void setValorNota(int valorNota) {
        this.valorNota.set(valorNota);
    }

    public IntegerProperty valorNota() {
        return this.valorNota;
    }

    //Relación con la clase DetalleActividad
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detalle_actividad_id")
    public DetalleActividad getDetalleActividad() {
        return detalleActividad;
    }

    public void setDetalleActividad(DetalleActividad detalleActividad) {
        this.detalleActividad = detalleActividad;
    }

    //
    @Column(name = "carne")
    public String getCarne() {
        return this.carne.get();
    }

    public void setCarne(String carne) {
        this.carne.set(carne);
    }

    public StringProperty carne() {
        return this.carne;
    }

    //
    @Override
    public String toString() {
        return this.getDetalleNotaId() + " " + this.getValorNota()
                + " " + this.getDetalleActividad() + " " + this.getCarne();
    }

    private static final long serialVersionUID = 1L;

}