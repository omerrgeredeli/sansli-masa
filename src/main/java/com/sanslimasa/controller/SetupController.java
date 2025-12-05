package com.sanslimasa.controller;

import com.sanslimasa.service.AdminUserService;
import com.sanslimasa.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SetupController {

    private final AdminUserService adminUserService;
    private final TableService tableService;

    @GetMapping("/setup")
    public String setupPage() {
        if (adminUserService.isSetupCompleted()) {
            return "redirect:/login";
        }
        return "setup"; // setup.html
    }

    @PostMapping("/setup")
    public String doSetup(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam int tableCount) {

        adminUserService.saveAdmin(username, password, tableCount);
        tableService.createDefaultTables(tableCount);

        return "redirect:/login";
    }
}
