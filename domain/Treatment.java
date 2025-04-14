package domain;

import java.util.*;

public class Treatment {
    private String name;
    private String areaOfExpertise;
    private List<Appointment> appointments;

    public Treatment(String name, String areaOfExpertise) {
        this.name = name;
        this.areaOfExpertise = areaOfExpertise;
        this.appointments = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getAreaOfExpertise() { return areaOfExpertise; }
    public List<Appointment> getAppointments() { return appointments; }

    public void addAppointment(Appointment appt) {
        appointments.add(appt);
    }

    public void displayInfo() {
        System.out.println("Treatment: " + name + " (" + areaOfExpertise + ")");
    }
}