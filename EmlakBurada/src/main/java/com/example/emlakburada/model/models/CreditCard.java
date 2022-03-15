package com.example.emlakburada.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit_card")
@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    private String nameOnCreditCard;
    private String creditCardNumber;
    private String shortFormatExpirationYear;
    private int CVVnumber;
    @OneToOne
    private User user;



    public CreditCard(String nameOnCreditCard, String creditCardNumber, String shortFormatExpirationYear, int CVVnumber, User user) {
        this.nameOnCreditCard = nameOnCreditCard;
        this.creditCardNumber = creditCardNumber;
        this.shortFormatExpirationYear = shortFormatExpirationYear;
        this.CVVnumber = CVVnumber;
        this.user = user;
    }
}