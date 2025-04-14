package service;

import domain.Physiotherapist;
import java.util.List;
import java.util.stream.Collectors;
import repository.PhysiotherapistRepository;

public class PhysiotherapistService {
    private final PhysiotherapistRepository repository;

    public PhysiotherapistService(PhysiotherapistRepository repository) {
        this.repository = repository;
    }

    public List<Physiotherapist> getByExpertise(String expertise) {
        return repository.getAll().stream()
                .filter(p -> p.getExpertise().contains(expertise))
                .collect(Collectors.toList());
    }

    public Physiotherapist getByName(String name) {
        return repository.getAll().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void listAllPhysiotherapists() {
        repository.getAll().forEach(Physiotherapist::displayInfo);
    }
}
