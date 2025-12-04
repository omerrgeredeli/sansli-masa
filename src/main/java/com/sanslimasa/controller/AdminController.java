package com.sanslimasa.controller;

import com.sanslimasa.model.Table;
import com.sanslimasa.service.TableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private final TableService tableService;

    public AdminController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/tables")
    public List<Table> getTables() {
        return tableService.getTables();
    }

    @PostMapping("/tables")
    public void addTable(@RequestBody Table table) {
        tableService.addTable(table);
    }

    @PutMapping("/tables/{number}")
    public void updateTable(@PathVariable int number, @RequestParam boolean active) {
        tableService.setActive(number, active);
    }
}
