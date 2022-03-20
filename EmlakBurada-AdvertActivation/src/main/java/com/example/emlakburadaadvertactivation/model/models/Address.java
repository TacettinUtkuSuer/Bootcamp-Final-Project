package com.example.emlakburadaadvertactivation.model.models;

import com.example.emlakburadaadvertactivation.model.enums.CountryType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CountryType country;
    private String district;
    private String fullAddress;

}