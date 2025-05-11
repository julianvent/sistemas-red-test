package uv.mx.sistemasredproject.server.rmi;

import uv.mx.sistemasredproject.interfaces.IMedicoService;

import java.rmi.Naming;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            IMedicoService medicoService = new MedicoServiceImpl();
            Naming.rebind("rmi://localhost/MedicoService", medicoService);
            System.out.println("Servidor RMI registrado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}