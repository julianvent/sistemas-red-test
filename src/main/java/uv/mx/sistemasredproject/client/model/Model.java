package uv.mx.sistemasredproject.client.model;

import uv.mx.sistemasredproject.client.views.ViewFactory;
import uv.mx.sistemasredproject.interfaces.IMedicoService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private IMedicoService medicoService;

    private Model() {
        viewFactory = new ViewFactory();
        initMedicoService();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    private void initMedicoService() {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            try {
                this.medicoService = (IMedicoService) registry.lookup("medicoService");
                System.out.println("Medico Service registrado");
            } catch (NotBoundException e) {
                throw new RuntimeException(e);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public IMedicoService getMedicoService() {
        return medicoService;
    }
}
