package com.example.emlakburada.model.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_product_package_id")
    @ToString.Exclude
    private List<Advert> adverts;
    private LocalDate packageExpirationDate;
    @OneToOne
    private User user;

}