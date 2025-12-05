package com.sanslimasa.controller;

import com.sanslimasa.model.Admin;
import com.sanslimasa.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AdminService adminService;

    // ✔ Login sayfası
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ✔ Signup sayfası (tek seferlik)
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    // ✔ Signup formunu işle
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password) {

        Admin existing = adminService.findByUsername(username);
        if (existing != null) {
            return "redirect:/signup?error=user-exists";
        }

        adminService.signup(username, password);

        return "redirect:/login?signup=ok";
    }
}
