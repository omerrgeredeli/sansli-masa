package com.sanslimasa.controller;

import com.sanslimasa.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AdminService adminService;

    /*@GetMapping("/login")
    public String loginPage() {

        // ✔ İlk açılışta default admin oluştur
        adminService.createDefaultAdmin();

        return "login";
    }*/
}
