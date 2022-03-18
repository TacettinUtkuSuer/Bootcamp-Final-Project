package com.example.emlakburada.service;

import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.enums.AdvertType;
import com.example.emlakburada.model.enums.CountryType;
import com.example.emlakburada.model.models.*;
import com.example.emlakburada.repository.AddressRepository;
import com.example.emlakburada.repository.AdvertRepository;
import com.example.emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PrepareDatabaseService {

    // It was created just to fill the database.
    // Normally, this class will not exist.

    @Autowired
    UserRepository userRepository;

    public void prepareDatabase(){

        User user = new User();
        user.setPhone("+90 555 444 33 22");
        user.setName("Tacettin Utku");
        user.setSurname("Suer");
        user.setEmail("tacettinutkusuer@gmail.com");
        user.setPassword("1993");

        AdvertProductPackage advertProductPackage = new AdvertProductPackage();
        advertProductPackage.setPackageExpirationDate(LocalDate.of(2022,4,11));
        advertProductPackage.setUser(user);


        List<Advert> adverts =  new ArrayList<>();

        Advert advert01 = new Advert();
        advert01.setAdvertType(AdvertType.FORSALE);
        advert01.setTitle("Acil satılık sahibinden ev!!!");
        advert01.setDefinition("3+1 Kombili 5.Kat");
        advert01.setAdvertStatus(AdvertStatus.ACTIVE);
        advert01.setAdvertProductPackage(advertProductPackage);
        advert01.setAddress(new Address(CountryType.ANKARA,"Çankaya","Bahçeli 11/43", advert01));
        adverts.add(advert01);

        Advert advert02 = new Advert();
        advert02.setAdvertType(AdvertType.FORRENT);
        advert02.setTitle("Otobüs ve metro durağına yakın merkezi ısıtma kiralık ev.");
        advert02.setDefinition("4+1 Merkezi Isıtma Giriş Katı");
        advert02.setAdvertStatus(AdvertStatus.PASSIVE);
        advert02.setAdvertProductPackage(advertProductPackage);
        advert02.setAddress(new Address(CountryType.ANKARA,"Çankaya","Mustafa Kemal 16/8", advert02));
        adverts.add(advert02);

        Advert advert03 = new Advert();
        advert03.setAdvertType(AdvertType.FORSALE);
        advert03.setTitle("Şehrin en merkezi yerinde satılık ev.");
        advert03.setDefinition("2+1 PayÖlçerli Teras");
        advert03.setAdvertStatus(AdvertStatus.ACTIVE);
        advert03.setAdvertProductPackage(advertProductPackage);
        advert03.setAddress(new Address(CountryType.ANKARA,"Çankaya","Çayyolu 14/23", advert03));
        adverts.add(advert03);

        advertProductPackage.setAdverts(adverts);

        user.setAdvertProductPackage(advertProductPackage);

        CreditCard creditCard = new CreditCard();
        creditCard.setNameOnCreditCard("Tacettin Utku Suer");
        creditCard.setCreditCardNumber("1111 2222 3333 4567");
        creditCard.setShortFormatExpirationYear("25");
        creditCard.setCVVnumber(123);
        creditCard.setUser(user);

        user.setCreditCard(creditCard);

        userRepository.save(user);
    }
}
