package com.sanslimasa.model;

import lombok.Data;

@Data
public class AdminUser {
    private String username;
    private String password;  // SHA256 hash formunda tutulacak
    private int tableCount;
}
