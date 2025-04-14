package main;

import domain.Appointment;
import domain.Patient;
import domain.Physiotherapist;
import domain.Treatment;
import java.util.*;
import repository.PatientRepository;
import repository.PhysiotherapistRepository;
import service.BookingService;
import service.PatientService;
import service.PhysiotherapistService;
import service.ReportService;
import utils.DataSeeder;

public class ClinicApp {
    public static void main(String[] args) {
        PatientRepository patientRepo = new PatientRepository();
        PhysiotherapistRepository physioRepo = new PhysiotherapistRepository();
        PatientService patientService = new PatientService(patientRepo);
        PhysiotherapistService physioService = new PhysiotherapistService(physioRepo);
        BookingService bookingService = new BookingService();
        ReportService reportService = new ReportService();

        DataSeeder.seedData(patientRepo, physioRepo);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Boost Physio Clinic System ---");
            System.out.println("1. View all physiotherapists");
            System.out.println("2. Search by expertise");
            System.out.println("3. Search physiotherapist by name");
            System.out.println("4. Book appointment");
            System.out.println("5. Cancel appointment");
            System.out.println("6. Check-in");
            System.out.println("7. Reports");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> physioService.listAllPhysiotherapists();
                case 2 -> {
                    System.out.print("Enter area of expertise: ");
                    String exp = sc.nextLine();
                    var physios = physioService.getByExpertise(exp);
                    physios.forEach(p -> {
                        p.displayInfo();
                        p.getTreatments().forEach(t -> {
                            t.displayInfo();
                            t.getAppointments().forEach(Appointment::displayInfo);
                        });
                    });
                }
                case 3 -> {
                    System.out.print("Enter physiotherapist name: ");
                    String name = sc.nextLine();
                    var p = physioService.getByName(name);
                    if (p != null) {
                        p.displayInfo();
                        p.getTreatments().forEach(t -> {
                            t.displayInfo();
                            t.getAppointments().forEach(Appointment::displayInfo);
                        });
                    } else System.out.println("Not found.");
                }
                case 4 -> {
                    System.out.print("Enter Patient ID: ");
                    int pid = Integer.parseInt(sc.nextLine());
                    Patient patient = patientService.getPatientById(pid);
                    if (patient == null) {
                        System.out.println("Invalid patient ID.");
                        break;
                    }
                    System.out.print("Enter Physiotherapist name: ");
                    Physiotherapist p = physioService.getByName(sc.nextLine());
                    if (p == null) {
                        System.out.println("Invalid physio.");
                        break;
                    }
                    List<Appointment> allAppointments = new ArrayList<>();
                    for (Treatment t : p.getTreatments()) allAppointments.addAll(t.getAppointments());
                    for (int i = 0; i < allAppointments.size(); i++) {
                        System.out.println("[" + i + "]");
                        allAppointments.get(i).displayInfo();
                    }
                    System.out.print("Choose appointment #: ");
                    int apptIndex = Integer.parseInt(sc.nextLine());
                    bookingService.bookAppointment(patient, allAppointments.get(apptIndex));
                }
                case 5 -> {
                    System.out.print("Enter Physiotherapist name: ");
                    var p = physioService.getByName(sc.nextLine());
                    if (p != null) {
                        List<Appointment> allAppointments = new ArrayList<>();
                        for (Treatment t : p.getTreatments()) allAppointments.addAll(t.getAppointments());
                        for (int i = 0; i < allAppointments.size(); i++) {
                            System.out.println("[" + i + "]");
                            allAppointments.get(i).displayInfo();
                        }
                        System.out.print("Choose appointment #: ");
                        int apptIndex = Integer.parseInt(sc.nextLine());
                        bookingService.cancelAppointment(allAppointments.get(apptIndex));
                    }
                }
                case 6 -> {
                    System.out.print("Enter Physiotherapist name: ");
                    var p = physioService.getByName(sc.nextLine());
                    if (p != null) {
                        List<Appointment> allAppointments = new ArrayList<>();
                        for (Treatment t : p.getTreatments()) allAppointments.addAll(t.getAppointments());
                        for (int i = 0; i < allAppointments.size(); i++) {
                            System.out.println("[" + i + "]");
                            allAppointments.get(i).displayInfo();
                        }
                        System.out.print("Choose appointment #: ");
                        int apptIndex = Integer.parseInt(sc.nextLine());
                        bookingService.checkIn(allAppointments.get(apptIndex));
                    }
                }
                case 7 -> {
                    List<Physiotherapist> allPhysios = new ArrayList<>(physioRepo.getAll());
                    reportService.generatePhysiotherapistReport(allPhysios);
                    reportService.listByAttendance(allPhysios);
                }
                case 0 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}