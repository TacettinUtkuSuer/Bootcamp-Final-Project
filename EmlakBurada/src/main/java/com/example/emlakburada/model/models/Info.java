package com.example.emlakburada.model.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "info")
@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    private String key;
    private String value;

}
