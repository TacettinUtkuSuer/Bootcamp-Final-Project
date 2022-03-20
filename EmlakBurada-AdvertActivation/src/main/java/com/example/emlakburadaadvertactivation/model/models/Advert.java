package com.example.emlakburadaadvertactivation.model.models;

import com.example.emlakburadaadvertactivation.model.enums.AdvertStatus;
import com.example.emlakburadaadvertactivation.model.enums.AdvertType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "advert")
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AdvertType advertType;
    @OneToOne
    @JoinColumn(name = "address_ID")
    private Address address;
    private String title;
    private String definition;
    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus;

}