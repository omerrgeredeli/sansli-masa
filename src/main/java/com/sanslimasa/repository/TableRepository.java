package com.sanslimasa.repository;

import com.sanslimasa.model.Table;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class TableRepository {

    // Eşzamanlı işlemlerde güvenli liste kullanıyoruz
    private final List<Table> tables = new CopyOnWriteArrayList<>();

    // Varsayılan Masaları Yükle
    public TableRepository() {
        tables.add(new Table(1, true));
        tables.add(new Table(2, false));
        tables.add(new Table(3, true));
        tables.add(new Table(4, false));
    }

    public List<Table> findAll() {
        // Masa numarasına göre sıralayarak daha düzenli bir görünüm sağlayabiliriz
        tables.sort(Comparator.comparingInt(Table::getNumber));
        return tables;
    }

    public void add(Table table) {
        // Aynı numaradan masa eklenmesini engellemek için kontrol ekleyelim
        if (tables.stream().noneMatch(t -> t.getNumber() == table.getNumber())) {
            tables.add(table);
        } else {
            System.out.println("Hata: Masa numarası " + table.getNumber() + " zaten mevcut.");
        }
    }

    public void update(int number, boolean active) {
        tables.stream()
                .filter(t -> t.getNumber() == number)
                .findFirst()
                .ifPresent(t -> t.setActive(active));
    }

    public List<Table> findActive() {
        // Java Stream ile daha kısa bir filtreleme
        return tables.stream()
                .filter(Table::isActive)
                .toList();
    }
}