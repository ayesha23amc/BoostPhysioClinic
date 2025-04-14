package service;

import domain.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReportService {

    public void generatePhysiotherapistReport(List<Physiotherapist> physiotherapists) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy, HH:mm");

    for (Physiotherapist p : physiotherapists) {
        System.out.println("\n==================== Report for Physiotherapist: " + p.getName() + " ====================\n");

        System.out.printf("%-50s %-30s %-15s %-15s %-20s%n",
        "Appointment Time", "Treatment", "Status", "Patient", "Physiotherapist");

        System.out.println("---------------------------------------------------------------------------------------------------------------");

        for (Treatment t : p.getTreatments()) {
            for (Appointment a : t.getAppointments()) {

                String appointmentTime = a.getStart().format(formatter) + " to " + a.getEnd().format(formatter);

                String treatment = t.getName();
                String status = a.getStatus().toString();
                String patientName = (a.getPatient() != null) ? a.getPatient().getName() : "-";
                String physioName = p.getName();

                System.out.printf("%-50s %-30s %-15s %-15s %-20s%n",
                    appointmentTime, treatment, status, patientName, physioName);
            }
        }
    }
}

    
    
    

    public void listByAttendance(List<Physiotherapist> physios) {
        physios.sort((a, b) -> {
            int countB = countAttended(b);
            int countA = countAttended(a);
            return Integer.compare(countB, countA);
        });

        System.out.println("\n--- Report for Physiotherapist In Descending Order: ---\n");
        for (Physiotherapist p : physios) {
            System.out.println(p.getName() + " -- Attended: " + countAttended(p));
        }
    }

    private int countAttended(Physiotherapist physio) {
        int count = 0;
        for (Treatment t : physio.getTreatments()) {
            for (Appointment a : t.getAppointments()) {
                if (a.getStatus() == Appointment.Status.ATTENDED) {
                    count++;
                }
            }
        }
        return count;
    }
}
