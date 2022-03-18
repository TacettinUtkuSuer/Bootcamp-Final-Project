package com.example.emlakburadaadvertactivation.model.models;

import com.example.emlakburadaadvertactivation.model.enums.CountryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    private CountryType country;
    private String district;
    private String fullAddress;

}