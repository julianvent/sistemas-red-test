package uv.mx.sistemasredproject.client.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uv.mx.sistemasredproject.client.views.ViewFactory;
import uv.mx.sistemasredproject.interfaces.IDoctorService;
import uv.mx.sistemasredproject.model.Appointment;
import uv.mx.sistemasredproject.model.Doctor;
import uv.mx.sistemasredproject.model.Patient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    // rmi client
    private IDoctorService medicoService;

    // db data
    private ObservableList<Doctor> doctorList;
    private ObservableList<Patient> patientList;
    private ObservableList<Appointment> appointmentList;

    private Model() {
        viewFactory = new ViewFactory();
        initMedicoService();
        doctorList = FXCollections.observableArrayList();
        patientList = FXCollections.observableArrayList();
        appointmentList = FXCollections.observableArrayList();
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
                this.medicoService = (IDoctorService) registry.lookup("medicoService");
                System.out.println("Medico Service registrado");
            } catch (NotBoundException e) {
                throw new RuntimeException(e);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public IDoctorService getMedicoService() {
        return medicoService;
    }

    public ObservableList<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setAllDoctors() {
        try {
            List<Doctor> doctors = medicoService.getDoctors();
            doctorList.setAll(doctors);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Patient> getPatientList() {
        return patientList;
    }

    public void setAllPatients() {
        try {
            List<Patient> patients = medicoService.getPatients();
            patientList.setAll(patients);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAllAppointments() {
        try {
            List<Appointment> appointments = medicoService.getAppointments();
            appointmentList.setAll(appointments);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
