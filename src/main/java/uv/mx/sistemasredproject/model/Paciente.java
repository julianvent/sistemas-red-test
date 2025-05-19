package uv.mx.sistemasredproject.model;

import javafx.beans.property.*;

public class Paciente {

    private IntegerProperty pacienteId;
    private StringProperty nombre;
    private StringProperty curp;
    private StringProperty telefono;
    private StringProperty correoElectronico;

    public Paciente(int pacienteId, String nombre, String curp, String telefono, String correoElectronico) {

        this.pacienteId = new SimpleIntegerProperty(this, "pacienteId", pacienteId);
        this.nombre = new SimpleStringProperty(this, "nombre", nombre);
        this.curp = new SimpleStringProperty(this, "curp", curp);
        this.telefono = new SimpleStringProperty(this, "telefono", telefono);
        this.correoElectronico = new SimpleStringProperty(this, "correoElectronico", correoElectronico);

    }

    public int getPacienteId() {
        return pacienteId.get();
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId.set(pacienteId);
    }

    public IntegerProperty pacienteIdProperty() {
        return pacienteId;
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

    public String getCurp() {
        return curp.get();
    }

    public void setCurp(String curp) {
        this.curp.set(curp);
    }

    public StringProperty curpProperty() {
        return curp;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public StringProperty telefonoProperty() {
        return telefono;
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