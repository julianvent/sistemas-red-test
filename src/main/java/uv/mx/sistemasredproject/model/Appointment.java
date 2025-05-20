package uv.mx.sistemasredproject.model;

import java.io.Serial;
import java.io.Serializable;

public class Appointment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String datetime;
    private String motivo;
    private int doctorId;
    private int patientId;

    public Appointment(int id, String datetime, String motivo, int doctorId, int patientId) {
        this.id = id;
        this.datetime = datetime;
        this.motivo = motivo;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
