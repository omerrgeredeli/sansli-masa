package com.sanslimasa.service;

import com.sanslimasa.model.Table;
import com.sanslimasa.repository.TableRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Table> getTables() {
        return tableRepository.findAll();
    }

    public void addTable(Table table) {
        tableRepository.add(table);
    }

    public void setActive(int number, boolean active) {
        tableRepository.update(number, active);
    }

    public List<Table> getActiveTables() {
        return tableRepository.findActive();
    }
}
