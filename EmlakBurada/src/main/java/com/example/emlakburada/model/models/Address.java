package com.example.emlakburada.model.models;

import com.example.emlakburada.model.enums.CountryType;
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Advert advert;

    public Address(CountryType country, String district, String fullAddress, Advert advert) {
        this.country = country;
        this.district = district;
        this.fullAddress = fullAddress;
        this.advert = advert;
    }
}