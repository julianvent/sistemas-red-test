package uv.mx.sistemasredproject.server.rmi;

import uv.mx.sistemasredproject.interfaces.IMedicoService;
import uv.mx.sistemasredproject.server.models.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MedicoServiceImpl extends UnicastRemoteObject implements IMedicoService {

    private final DatabaseDriver db;

    public MedicoServiceImpl() throws RemoteException {
        db = new DatabaseDriver();
    }

    @Override
    public void agregarMedico(String nombre, String especialidad, String cedula, String correoElectronico) {
        db.agregarMedico(nombre, especialidad, cedula, correoElectronico);
    }

    @Override
    public void actualizarMedico(int medicoId, String nombre, String especialidad, String cedula, String correoElectronico) {
        db.actualizarMedico(medicoId, nombre, especialidad, cedula, correoElectronico);
    }

    @Override
    public void eliminarMedico(int medicoId) {
        db.eliminarMedico(medicoId);
    }

    @Override
    public List<Medico> listarMedicos() {
        return db.obtenerMedicos();
    }

    @Override
    public void agregarPaciente(String nombre, String curp, String telefono, String correo) throws RemoteException{
        db.agregarPaciente(nombre, curp, telefono, correo);
    }
    
    @Override
    public void actualizarPaciente(int pacienteId, String nombre, String curp, String telefono) throws RemoteException{
        db.actualizarPaciente(pacienteId, nombre, curp, telefono);
    }

    @Override
    public void eliminarPaciente(int pacienteId) throws RemoteException{
        db.eliminarPaciente(pacienteId);
    }
    @Override
    public List<Paciente> listarPacientes() throws RemoteException{
        return db.obtenerPacientes();
    }

    @Override
    public void agregarCita(String fechaHora, String motivo, int medicoId, int pacienteId) throws RemoteException{
        db.agregarCita(fechaHora, motivo, medicoId, pacienteId);
    }

    @Override
    public void actualizarCita(int citaId, String fechaHora, String motivo, int medicoId) throws RemoteException{
        db.actualizarCita( citaId, fechaHora, motivo, medicoId);
    }

    @Override
    public void eliminarCita(int citaId) throws RemoteException{
        db.eliminarCita(citaId);
    }

    @Override
    public List<Cita> listarCitas() throws RemoteException{
        return db.obtenerCitas();
    }
}
