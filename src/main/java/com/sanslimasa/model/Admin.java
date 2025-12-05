package com.sanslimasa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    // İlk kurulum tamamlandı mı?
    private boolean setupCompleted;
}
