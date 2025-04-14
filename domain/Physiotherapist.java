package domain;

import java.util.*;

public class Physiotherapist extends Member {
    private Set<String> expertise;
    private List<Treatment> treatments;

    public Physiotherapist(int id, String name, String address, String phone, Set<String> expertise) {
        super(id, name, address, phone);
        this.expertise = expertise;
        this.treatments = new ArrayList<>();
    }

    public Set<String> getExpertise() { return expertise; }
    public List<Treatment> getTreatments() { return treatments; }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    @Override
    public void displayInfo() {
        System.out.println("--- Physiotherapist Info ---");
        super.displayInfo();
        System.out.println("Expertise: " + String.join(", ", expertise));
    }
}
