package com.sanslimasa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanslimasa.model.AdminUser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.security.MessageDigest;

@Service
public class AdminUserService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File adminFile = new File("admin-user.json");

    private AdminUser admin;

    public boolean isSetupCompleted() {
        return adminFile.exists();
    }

    public AdminUser getAdmin() {
        try {
            if (admin == null && adminFile.exists()) {
                admin = mapper.readValue(adminFile, AdminUser.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void saveAdmin(String username, String rawPassword, int tableCount) {
        try {
            admin = new AdminUser();
            admin.setUsername(username);
            admin.setPassword(hash(rawPassword));
            admin.setTableCount(tableCount);

            mapper.writerWithDefaultPrettyPrinter().writeValue(adminFile, admin);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String rawPassword) {
        AdminUser a = getAdmin();
        if (a == null) return false;

        return a.getUsername().equals(username)
                && a.getPassword().equals(hash(rawPassword));
    }

    private String hash(String raw) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = digest.digest(raw.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : encoded) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
