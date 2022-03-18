package com.example.emlakburada.model.models;

import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.enums.AdvertType;
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
    private String title;
    private String definition;
    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus;
    @ManyToOne
    private AdvertProductPackage advertProductPackage;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_ID")
    private Address address;



}