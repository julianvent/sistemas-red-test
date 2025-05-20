package uv.mx.sistemasredproject.interfaces;

import uv.mx.sistemasredproject.model.Appointment;
import uv.mx.sistemasredproject.model.Doctor;
import uv.mx.sistemasredproject.model.Patient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IDoctorService extends Remote {

    void addDoctor(String nombre, String especialidad, String cedula, String correoElectronico) throws RemoteException;
    void updateDoctor(int medicoId, String nombre, String especialidad, String cedula, String correoElectronico) throws RemoteException;
    void deleteDoctor(int medicoId) throws RemoteException;
    Doctor getDoctor(int medicoId) throws RemoteException;
    List<Doctor> getDoctors() throws RemoteException;

    void addPatient(String nombre, String curp, String telefono, String correo) throws RemoteException;
    void updatePatient(int pacienteId, String nombre, String curp, String telefono) throws RemoteException;
    void deletePatient(int pacienteId) throws RemoteException;
    List<Patient> getPatients() throws RemoteException;
    Patient getPatient(int pacienteId) throws RemoteException;

    void addAppointment(String fechaHora, String motivo, int medicoId, int pacienteId) throws RemoteException;
    void updateAppointment(int citaId, String fechaHora, String motivo, int medicoId) throws RemoteException;
    void deleteAppointment(int citaId) throws RemoteException;
    List<Appointment> getAppointments() throws RemoteException;
}
