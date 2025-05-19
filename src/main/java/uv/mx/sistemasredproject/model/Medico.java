package uv.mx.sistemasredproject.model;

import javafx.beans.property.*;

public class Medico {

    private IntegerProperty medicoId;
    private StringProperty nombre;
    private StringProperty especialidad;
    private StringProperty cedula;
    private StringProperty correoElectronico;

    public Medico(int medicoId, String nombre, String especialidad, String cedula, String correoElectronico) {

        this.medicoId = new SimpleIntegerProperty(this, "medicoId", medicoId);
        this.nombre = new SimpleStringProperty(this, "nombre", nombre);
        this.especialidad = new SimpleStringProperty(this, "especialidad", especialidad);
        this.cedula = new SimpleStringProperty(this, "cedula", cedula);
        this.correoElectronico = new SimpleStringProperty(this, "correoElectronico", correoElectronico);

    }

    public int getMedicoId() {
        return medicoId.get();
    }

    public void setMedicoId(int medicoId) {
        this.medicoId.set(medicoId);
    }

    public IntegerProperty medicoIdProperty() {
        return medicoId;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad.get();
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad.set(especialidad);
    }

    public StringProperty especialidadProperty() {
        return especialidad;
    }

    public String getCedula() {
        return cedula.get();
    }

    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }

    public StringProperty cedulaProperty() {
        return cedula;
    }

    public String getCorreoElectronico() {
        return correoElectronico.get();
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico.set(correoElectronico);
    }

    public StringProperty correoElectronicoProperty() {
        return correoElectronico;
    }
}