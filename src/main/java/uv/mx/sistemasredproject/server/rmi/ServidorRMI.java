package uv.mx.sistemasredproject.server.rmi;

import uv.mx.sistemasredproject.interfaces.IDoctorService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            IDoctorService medicoService = new DoctorServiceImpl();
            registry.rebind("medicoService", medicoService);
            System.out.println("Servidor RMI registrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}