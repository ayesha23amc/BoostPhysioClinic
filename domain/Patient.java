package domain;

public class Patient extends Member {
    public Patient(int id, String name, String address, String phone) {
        super(id, name, address, phone);
    }

    @Override
    public void displayInfo() {
        System.out.println("--- Patient Info ---");
        super.displayInfo();
    }
}