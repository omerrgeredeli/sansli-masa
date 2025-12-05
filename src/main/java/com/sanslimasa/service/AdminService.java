package com.sanslimasa.service;

import com.sanslimasa.model.Admin;
import com.sanslimasa.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    // ✔ Kullanıcıyı oluştur (Signup)
    public Admin signup(String username, String rawPassword) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(rawPassword));
        admin.setSetupCompleted(false);
        return adminRepository.save(admin);
    }

    // ✔ İlk çalıştırmada default admin oluştur
    public void createDefaultAdmin() {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setSetupCompleted(false);
            adminRepository.save(admin);
            System.out.println("Default admin created: admin / admin123");
        }
    }

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }

    public boolean validatePassword(Admin admin, String rawPassword) {
        return passwordEncoder.matches(rawPassword, admin.getPassword());
    }

    public void markSetupCompleted(Admin admin) {
        admin.setSetupCompleted(true);
        adminRepository.save(admin);
    }

    public boolean isSetupCompleted(Admin admin) {
        return admin.isSetupCompleted();
    }
}
