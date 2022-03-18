package com.example.emlakburada.model.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_product_package_ID")
    private AdvertProductPackage advertProductPackage;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_card_ID")
    private CreditCard creditCard;

}
