package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    public enum Status { AVAILABLE, BOOKED, CANCELLED, ATTENDED }

    private LocalDateTime start;
    private LocalDateTime end;
    private Physiotherapist physio;
    private Treatment treatment;
    private Patient patient;
    private Status status;

    public Appointment(LocalDateTime start, LocalDateTime end, Physiotherapist physio, Treatment treatment) {
        this.start = start;
        this.end = end;
        this.physio = physio;
        this.treatment = treatment;
        this.status = Status.AVAILABLE;
    }

    public void assignPatient(Patient p) {
        this.patient = p;
        this.status = Status.BOOKED;
    }

    public void cancel() {
        this.status = Status.CANCELLED;
    }

    public void checkIn() {
        this.status = Status.ATTENDED;
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public Physiotherapist getPhysio() { return physio; }
    public Treatment getTreatment() { return treatment; }
    public Patient getPatient() { return patient; }
    public Status getStatus() { return status; }

    public void displayInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy, HH:mm");
        System.out.println("- Appointment Time: " + start.format(formatter) + " to " + end.format(formatter));
        System.out.println("  Treatment: " + treatment.getName());
        System.out.println("  Physiotherapist: " + physio.getName());
        System.out.println("  Status: " + status);
        if (patient != null) {
            System.out.println("  Patient: " + patient.getName());
        }
    }
}