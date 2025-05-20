package uv.mx.sistemasredproject.server.rmi;

import uv.mx.sistemasredproject.interfaces.IDoctorService;
import uv.mx.sistemasredproject.model.Appointment;
import uv.mx.sistemasredproject.model.Doctor;
import uv.mx.sistemasredproject.model.Patient;
import uv.mx.sistemasredproject.server.models.DatabaseDriver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DoctorServiceImpl extends UnicastRemoteObject implements IDoctorService {

    private final DatabaseDriver db;

    public DoctorServiceImpl() throws RemoteException {
        db = new DatabaseDriver();
    }

    @Override
    public void addDoctor(String nombre, String especialidad, String cedula, String correoElectronico) {
        db.agregarMedico(nombre, especialidad, cedula, correoElectronico);
    }

    @Override
    public void updateDoctor(int medicoId, String nombre, String especialidad, String cedula, String correoElectronico) {
        db.actualizarMedico(medicoId, nombre, especialidad, cedula, correoElectronico);
    }

    @Override
    public void deleteDoctor(int medicoId) {
        db.eliminarMedico(medicoId);
    }

    @Override
    public List<Doctor> getDoctors() {
        return db.obtenerMedicos();
    }

    @Override
    public Doctor getDoctor(int medicoId) {
        return db.getMedico(medicoId);
    }

    @Override
    public void addPatient(String nombre, String curp, String telefono, String correo) throws RemoteException{
        db.agregarPaciente(nombre, curp, telefono, correo);
    }
    
    @Override
    public void updatePatient(int pacienteId, String nombre, String curp, String telefono) throws RemoteException{
        db.actualizarPaciente(pacienteId, nombre, curp, telefono);
    }

    @Override
    public void deletePatient(int pacienteId) throws RemoteException{
        db.eliminarPaciente(pacienteId);
    }
    @Override
    public List<Patient> getPatients() throws RemoteException{
        return db.obtenerPacientes();
    }

    @Override
    public Patient getPatient(int pacienteId) throws RemoteException {
        return db.getPaciente(pacienteId);
    }

    @Override
    public void addAppointment(String fechaHora, String motivo, int medicoId, int pacienteId) throws RemoteException{
        db.agregarCita(fechaHora, motivo, medicoId, pacienteId);
    }

    @Override
    public void updateAppointment(int citaId, String fechaHora, String motivo, int medicoId) throws RemoteException{
        db.actualizarCita( citaId, fechaHora, motivo, medicoId);
    }

    @Override
    public void deleteAppointment(int citaId) throws RemoteException{
        db.eliminarCita(citaId);
    }

    @Override
    public List<Appointment> getAppointments() throws RemoteException{
        return db.obtenerCitas();
    }
}
