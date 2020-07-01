package edu.carlospaz.kalum.models;

import java.io.Serializable;
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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Table(name = "modulo")
@NamedQueries({@NamedQuery(name = "Modulo.findAll", query = "select m from Modulo m")})
public class Modulo implements Serializable {
    private final StringProperty moduloId;
    private final StringProperty nombreModulo;
    private CarreraTecnica carrera;
    private final IntegerProperty numeroSeminarios;
    private List<Seminario> seminarios;

    public Modulo() {
        this.moduloId = new SimpleStringProperty();
        this.nombreModulo = new SimpleStringProperty();
        this.numeroSeminarios = new SimpleIntegerProperty();
    }

    public Modulo(String moduloId, String nombreModulo, String carreraId, int numeroSeminarios) {
        this.moduloId = new SimpleStringProperty();
        this.nombreModulo = new SimpleStringProperty();
        this.numeroSeminarios = new SimpleIntegerProperty();
    }

    //
    @Id
    @Column(name = "modulo_id")
    public String getModuloId() {
        return this.moduloId.get();
    }

    public void setModuloId(String moduloId) {
        this.moduloId.set(moduloId);
    }

    public StringProperty moduloId() {
        return this.moduloId;
    }
    //
    @Column(name = "nombre_modulo")
    public String getNombreModulo() {
        return this.nombreModulo.get();
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo.set(nombreModulo);
    }

    public StringProperty nombreModulo() {
        return this.nombreModulo;
    }

    //Relación con la clase CarreraTecnica
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carrera_id", referencedColumnName = "codigo_carrera")
    public CarreraTecnica getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraTecnica carrera) {
        this.carrera = carrera;
    }

    //
    @Column(name = "numero_seminarios")
    public int getNumeroSeminarios() {
        return this.numeroSeminarios.get();
    }

    public void setNumeroSeminarios(int numeroSeminarios) {
        this.numeroSeminarios.set(numeroSeminarios);
    }

    public IntegerProperty numeroSeminarios() {
        return this.numeroSeminarios;
    }

    //Relación con la clase Seminario
    @OneToMany(mappedBy = "modulo", fetch = FetchType.EAGER)
    public List<Seminario> getSeminarios() {
        return seminarios;
    }

    public void setSeminarios(List<Seminario> seminarios) {
        this.seminarios = seminarios;
    }

    //
    @Override
    public String toString() {
        return this.moduloId() + " " + this.getNombreModulo()
                + " " + this.getCarrera() + " " + this.getNumeroSeminarios();
    }

    private static final long serialVersionUID = 1L;

}