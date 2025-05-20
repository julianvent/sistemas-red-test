package uv.mx.sistemasredproject.model;

import java.io.Serializable;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String curp;
    private String phone;
    private String email;
    private Boolean eliminado;

    public Patient(int id, String name, String curp, String phone, String email) {
        this.id = id;
        this.name = name;
        this.curp = curp;
        this.phone = phone;
        this.email = email;
        this.eliminado = false;
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

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEliminado() {
            return eliminado;
    }
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}