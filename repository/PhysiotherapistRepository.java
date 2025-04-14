package repository;

import domain.Physiotherapist;
import java.util.*;

public class PhysiotherapistRepository {
    private final Map<Integer, Physiotherapist> physios = new HashMap<>();

    public void add(Physiotherapist p) {
        physios.put(p.getId(), p);
    }

    public Physiotherapist getById(int id) {
        return physios.get(id);
    }

    public Collection<Physiotherapist> getAll() {
        return physios.values();
    }
}