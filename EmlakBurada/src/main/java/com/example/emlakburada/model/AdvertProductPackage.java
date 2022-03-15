package com.example.emlakburada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "advert_product_package")
@Entity
public class AdvertProductPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @OneToMany
    @JoinColumn(name = "advert_ID")
    private Set<Advert> advert;
    private Date packageExpirationDate;


}