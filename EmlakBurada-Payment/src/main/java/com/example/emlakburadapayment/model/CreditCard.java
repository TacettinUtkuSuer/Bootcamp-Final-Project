package com.example.emlakburadapayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    private String nameOnCreditCard;
    private String creditCardNumber;
    private String shortFormatExpirationYear;
    private int CVVnumber;

}