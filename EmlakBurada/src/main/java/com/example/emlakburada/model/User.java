package com.example.emlakburada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    private String phone;
    private String name;
    private String surname;
    private String email;
    private String password;
    @OneToMany
    @JoinColumn(name = "advert_product_package_ID")
    private Set<AdvertProductPackage> advertProductPackage;
    @OneToOne
    @JoinColumn(name = "credit_card_ID")
    private CreditCard creditCard;


}
