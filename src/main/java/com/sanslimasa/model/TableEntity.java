package com.sanslimasa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private boolean active;
}
