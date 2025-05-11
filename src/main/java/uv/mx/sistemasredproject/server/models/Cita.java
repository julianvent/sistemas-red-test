package uv.mx.sistemasredproject.server.models;


import javafx.beans.property.*;

public class Cita {

    private IntegerProperty citaId;
    private StringProperty fechaHora;
    private StringProperty motivo;
    private IntegerProperty medicoId;
    private IntegerProperty pacienteId;

    public Cita(int citaId, String fechaHora, String motivo, int medicoId, int pacienteId) {

        this.citaId = new SimpleIntegerProperty(this, "citaId", citaId);
        this.fechaHora = new SimpleStringProperty(this, "fechaHora", fechaHora);
        this.motivo = new SimpleStringProperty(this, "motivo", motivo);
        this.medicoId = new SimpleIntegerProperty(this, "medicoId", medicoId);
        this.pacienteId = new SimpleIntegerProperty(this, "pacienteId", pacienteId);

    }

    public int getCitaId() {
        return citaId.get();
    }

    public void setCitaId(int citaId) {
        this.citaId.set(citaId);
    }

    public IntegerProperty citaIdProperty() {
        return citaId;
    }

    public String getFechaHora() {
        return fechaHora.get();
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora.set(fechaHora);
    }

    public StringProperty fechaHoraProperty() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo.get();
    }

    public void setMotivo(String motivo) {
        this.motivo.set(motivo);
    }

    public StringProperty motivoProperty() {
        return motivo;
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

    public int getPacienteId() {
        return pacienteId.get();
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId.set(pacienteId);
    }

    public IntegerProperty pacienteIdProperty() {
        return pacienteId;
    }
}