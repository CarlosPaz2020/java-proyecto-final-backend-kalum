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
@Table(name = "alumno")
@NamedQueries({@NamedQuery(name = "Alumno.findAll", query = "select a from Alumno a")})
public class Alumno implements Serializable {
    private final StringProperty carne;
    private final StringProperty noExpediente;
    private final StringProperty apellidos;
    private final StringProperty nombres;
    private final StringProperty email;
    private List<AsignacionAlumno> asignaciones;

    public Alumno() {
        this.carne = new SimpleStringProperty();
        this.noExpediente = new SimpleStringProperty();
        this.apellidos = new SimpleStringProperty();
        this.nombres = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    public Alumno(String carne, String noExpediente, String apellidos, String nombres, String email) {
        this.carne = new SimpleStringProperty();
        this.noExpediente = new SimpleStringProperty();
        this.apellidos = new SimpleStringProperty();
        this.nombres = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    //
    @Id
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
    @Column(name = "no_expediente")
    public String getNoExpediente() {
        return this.noExpediente.get();
    }

    public void setNoExpediente(String noExpediente) {
        this.noExpediente.set(noExpediente);
    }

    public StringProperty noExpediente() {
        return this.noExpediente;
    }

    //
    @Column(name = "apellidos")
    public String getApellidos() {
        return this.apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidos() {
        return this.apellidos;
    }

    //
    @Column(name = "nombres")
    public String getNombres() {
        return this.nombres.get();
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public StringProperty nombres() {
        return this.nombres;
    }

    //
    @Column(name = "email")
    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty email() {
        return this.email;
    }

    //Relación con la clase AsignacionAlumno
    @OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER)
    public List<AsignacionAlumno> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<AsignacionAlumno> asignaciones) {
        this.asignaciones = asignaciones;
    }

    //
    @Override
    public String toString() {
        return this.getCarne() + " " + this.getNoExpediente()
                + " " + this.getApellidos() + " " + this.getNoExpediente()
                + " " + this.getEmail();
    }

    private static final long serialVersionUID = 1L;

}