package service;

import domain.Patient;
import repository.PatientRepository;

public class PatientService {
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public void addPatient(Patient patient) {
        repository.add(patient);
        System.out.println("Patient added successfully.");
    }

    public void removePatient(int id) {
        if (repository.remove(id)) {
            System.out.println("Patient removed.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    public Patient getPatientById(int id) {
        return repository.getById(id);
    }

    public void listAllPatients() {
        repository.getAll().forEach(Patient::displayInfo);
    }
}
