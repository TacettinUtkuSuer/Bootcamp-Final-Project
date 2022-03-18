package com.example.emlakburadaadvertactivation.model.models;

import com.example.emlakburadaadvertactivation.model.enums.AdvertStatus;
import com.example.emlakburadaadvertactivation.model.enums.AdvertType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "advert")
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    private AdvertType advertType;
    @OneToOne
    @JoinColumn(name = "address_ID")
    private Address address;
    private String title;
    private String definition;
    private AdvertStatus advertStatus;

}