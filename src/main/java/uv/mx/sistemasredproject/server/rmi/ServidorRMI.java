package uv.mx.sistemasredproject.server.rmi;

import uv.mx.sistemasredproject.interfaces.IMedicoService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            IMedicoService medicoService = new MedicoServiceImpl();
            Naming.rebind("rmi://localhost/MedicoService", medicoService);
            System.out.println("Servidor RMI registrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}