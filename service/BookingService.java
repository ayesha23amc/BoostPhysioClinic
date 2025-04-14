package service;

import domain.*;

public class BookingService {

    public void bookAppointment(Patient patient, Appointment appointment) {
        if (appointment.getStatus() == Appointment.Status.AVAILABLE && appointment.getPatient() == null) {
            appointment.assignPatient(patient);
            System.out.println("✅ Appointment booked successfully.");
        } else {
            System.out.println("❌ Cannot book — appointment already taken or unavailable.");
        }
    }

    public void cancelAppointment(Appointment appointment) {
        if (appointment.getStatus() == Appointment.Status.BOOKED) {
            appointment.cancel();
            System.out.println("🗑️ Appointment cancelled.");
        } else {
            System.out.println("❌ Cannot cancel — already cancelled or attended.");
        }
    }

    public void checkIn(Appointment appointment) {
        if (appointment.getStatus() == Appointment.Status.BOOKED) {
            appointment.checkIn();
            System.out.println("🟢 Patient checked in.");
        } else {
            System.out.println("❌ Cannot check in — appointment not active.");
        }
    }
}
