package repository;

import domain.Patient;
import java.util.*;

public class PatientRepository {
    private final Map<Integer, Patient> patients = new HashMap<>();

    public void add(Patient patient) {
        patients.put(patient.getId(), patient);
    }

    public boolean remove(int id) {
        return patients.remove(id) != null;
    }

    public Patient getById(int id) {
        return patients.get(id);
    }

    public Collection<Patient> getAll() {
        return patients.values();
    }
}