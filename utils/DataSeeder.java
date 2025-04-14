package utils;

import domain.*;
import java.time.LocalDateTime;
import java.util.*;
import repository.*;

public class DataSeeder {
    public static void seedData(PatientRepository patientRepo, PhysiotherapistRepository physioRepo) {
        for (int i = 1; i <= 10; i++) {
            patientRepo.add(new Patient(i, "Patient " + i, "Address " + i, "1234567890"));
        }

        String[] expertiseList = {"Physiotherapy", "Osteopathy", "Rehabilitation"};
        String[] treatmentNames = {"Massage", "Acupuncture", "Pool rehabilitation", "Mobilisation of the spine"};

        for (int i = 1; i <= 3; i++) {
            Set<String> expertise = new HashSet<>();
            expertise.add(expertiseList[i % 3]);

            Physiotherapist p = new Physiotherapist(100 + i, "Physio " + i, "Clinic Address", "987654321", expertise);

            for (String treatmentName : treatmentNames) {
                Treatment treatment = new Treatment(treatmentName, expertiseList[i % 3]);
                for (int w = 0; w < 4; w++) {
                    LocalDateTime start = LocalDateTime.of(2025, 5, 1 + w * 7, 10 + i, 0);
                    LocalDateTime end = start.plusHours(1);
                    Appointment appointment = new Appointment(start, end, p, treatment);
                    treatment.addAppointment(appointment);
                }
                p.addTreatment(treatment);
            }

            physioRepo.add(p);
        }
    }
}