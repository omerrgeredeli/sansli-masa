package com.sanslimasa.repository;

import com.sanslimasa.model.Table;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TableRepository {

    private final List<Table> tables = new ArrayList<>();

    public List<Table> findAll() {
        return tables;
    }

    public void add(Table table) {
        tables.add(table);
    }

    public void update(int number, boolean active) {
        tables.stream()
                .filter(t -> t.getNumber() == number)
                .findFirst()
                .ifPresent(t -> t.setActive(active));
    }

    public List<Table> findActive() {
        List<Table> active = new ArrayList<>();
        for (Table t : tables) {
            if (t.isActive()) active.add(t);
        }
        return active;
    }
}
