package com.sanslimasa.controller;

import com.sanslimasa.model.Admin;
import com.sanslimasa.model.TableEntity;
import com.sanslimasa.service.AdminService;
import com.sanslimasa.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final TableService tableService;

    // İlk kurulum ekranı
    @GetMapping("/setup")
    public String setupPage() {
        return "setup";
    }

    // İlk kurulum POST (masa sayısı kaydedilir)
    @PostMapping("/setup")
    public String setupComplete(@RequestParam int tableCount, Principal principal) {

        // Admin'i getir
        Admin admin = adminService.findByUsername(principal.getName());

        // Masaları oluştur
        tableService.initializeTables(tableCount);

        // Admin setup completed olarak işaretle
        adminService.markSetupCompleted(admin);

        // Admin paneline yönlendir
        return "redirect:/admin/panel";
    }

    // Admin paneli
    @GetMapping("/panel")
    public String panel() {
        return "admin";
    }

    // API → masaları getir
    @GetMapping("/tables")
    @ResponseBody
    public List<TableEntity> getTables() {
        return tableService.getAll();
    }

    // API → masa ekle
    @PostMapping("/tables")
    @ResponseBody
    public TableEntity addTable(@RequestBody TableEntity table) {
        return tableService.addTable(table.getNumber(), table.isActive());
    }

    // API → aktif/pasif güncelle
    @PutMapping("/tables/{num}")
    @ResponseBody
    public void updateTable(
            @PathVariable int num,
            @RequestParam boolean active
    ) {
        tableService.setActive(num, active);
    }
}
