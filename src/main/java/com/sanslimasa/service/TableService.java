package com.sanslimasa.service;

import com.sanslimasa.model.TableEntity;
import com.sanslimasa.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    // --------------------------------------------
    // 1) Setup aşamasında ilk kez masaları oluşturur
    // --------------------------------------------
    public void initializeTables(int count) {
        tableRepository.deleteAll(); // Temiz başla

        for (int i = 1; i <= count; i++) {
            TableEntity t = new TableEntity();
            t.setNumber(i);
            t.setActive(false);
            tableRepository.save(t);
        }
    }

    // --------------------------------------------
    // 2) Alternatif setup metodu: createDefaultTables
    // SetupController için
    // --------------------------------------------
    public void createDefaultTables(int count) {
        initializeTables(count);
    }

    // --------------------------------------------
    // 3) Tüm masaları getir
    // --------------------------------------------
    public List<TableEntity> getAll() {
        return tableRepository.findAll();
    }

    // --------------------------------------------
    // 4) Yeni masa ekle
    // --------------------------------------------
    public TableEntity addTable(int number, boolean active) {

        // Önce var mı kontrol et (idempotent)
        TableEntity exists = tableRepository.findByNumber(number);
        if (exists != null)
            return exists;

        TableEntity t = new TableEntity();
        t.setNumber(number);
        t.setActive(active);

        return tableRepository.save(t);
    }

    // --------------------------------------------
    // 5) Masa aktif/pasif yap
    // --------------------------------------------
    public void setActive(int number, boolean active) {
        TableEntity t = tableRepository.findByNumber(number);

        if (t != null) {
            t.setActive(active);
            tableRepository.save(t);
        }
    }

    // --------------------------------------------
    // 6) Aktif masaları getir
    // --------------------------------------------
    public List<TableEntity> getActiveTables() {
        return tableRepository.findAll().stream()
                .filter(TableEntity::isActive)
                .toList();
    }
}
