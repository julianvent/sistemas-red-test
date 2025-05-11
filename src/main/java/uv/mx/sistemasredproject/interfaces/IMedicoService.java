package uv.mx.sistemasredproject.interfaces;

import uv.mx.sistemasredproject.server.models.Cita;
import uv.mx.sistemasredproject.server.models.Medico;
import uv.mx.sistemasredproject.server.models.Paciente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IMedicoService extends Remote {

    void agregarMedico(String nombre, String especialidad, String cedula, String correoElectronico) throws RemoteException;
    void actualizarMedico(int medicoId, String nombre, String especialidad, String cedula, String correoElectronico) throws RemoteException;
    void eliminarMedico(int medicoId) throws RemoteException;
    List<Medico> listarMedicos() throws RemoteException;

    void agregarPaciente(String nombre, String curp, String telefono, String correo) throws RemoteException;
    void actualizarPaciente(int pacienteId, String nombre, String curp, String telefono) throws RemoteException;
    void eliminarPaciente(int pacienteId) throws RemoteException;
    List<Paciente> listarPacientes() throws RemoteException;

    void agregarCita(String fechaHora, String motivo, int medicoId, int pacienteId) throws RemoteException;
    void actualizarCita(int citaId, String fechaHora, String motivo, int medicoId) throws RemoteException;
    void eliminarCita(int citaId) throws RemoteException;
    List<Cita> listarCitas() throws RemoteException;
}
