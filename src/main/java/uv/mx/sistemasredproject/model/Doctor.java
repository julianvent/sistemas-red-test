package uv.mx.sistemasredproject.model;

import java.io.Serial;
import java.io.Serializable;

public class Doctor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String degree;
    private String cedula;
    private String email;

    public Doctor(int id, String name, String degree, String cedula, String email) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.cedula = cedula;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
