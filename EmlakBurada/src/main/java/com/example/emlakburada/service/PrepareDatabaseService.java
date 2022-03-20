package com.example.emlakburada.service;

import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.enums.AdvertType;
import com.example.emlakburada.model.enums.CountryType;
import com.example.emlakburada.model.models.*;
import com.example.emlakburada.repository.InfoRepository;
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

    public void prepareDatabase01(){

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

        Advert advert04 = new Advert();
        advert04.setAdvertType(AdvertType.DAILYRENT);
        advert04.setTitle("Residance'ta günlük kiralık");
        advert04.setDefinition("1+1");
        advert04.setAdvertStatus(AdvertStatus.ACTIVE);
        advert04.setAdvertProductPackage(advertProductPackage);
        advert04.setAddress(new Address(CountryType.ANKARA,"Yenimahalle","Çukurambar 4/67", advert04));
        adverts.add(advert04);

        Advert advert05 = new Advert();
        advert05.setAdvertType(AdvertType.FORSALE);
        advert05.setTitle("Yenilenmiş, bakımlı, temiz ev");
        advert05.setDefinition("2+1 Merkezi Şömineli");
        advert05.setAdvertStatus(AdvertStatus.PASSIVE);
        advert05.setAdvertProductPackage(advertProductPackage);
        advert05.setAddress(new Address(CountryType.ANKARA,"Çankaya","Mustafa Kemal 89/34", advert05));
        adverts.add(advert05);

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

    public void prepareDatabase02(){

        User user = new User();
        user.setPhone("+90 555 444 33 22");
        user.setName("Cem");
        user.setSurname("Özer");
        user.setEmail("cemozer@gmail.com");
        user.setPassword("1991");

        AdvertProductPackage advertProductPackage = new AdvertProductPackage();
        advertProductPackage.setPackageExpirationDate(LocalDate.of(2022,5,14));
        advertProductPackage.setUser(user);


        List<Advert> adverts =  new ArrayList<>();

        Advert advert01 = new Advert();
        advert01.setAdvertType(AdvertType.FORSALE);
        advert01.setTitle("Acil kiralık ev!!!");
        advert01.setDefinition("5+1 Yerden Isıtma 4.Kat");
        advert01.setAdvertStatus(AdvertStatus.ACTIVE);
        advert01.setAdvertProductPackage(advertProductPackage);
        advert01.setAddress(new Address(CountryType.ANKARA,"Yenimahalle","Batıkent 10/3", advert01));
        adverts.add(advert01);

        Advert advert02 = new Advert();
        advert02.setAdvertType(AdvertType.FORRENT);
        advert02.setTitle("Ankara'nın göbeğinde...");
        advert02.setDefinition("2+1 Havuzlu Sitede");
        advert02.setAdvertStatus(AdvertStatus.PASSIVE);
        advert02.setAdvertProductPackage(advertProductPackage);
        advert02.setAddress(new Address(CountryType.ANKARA,"Çankaya","Yaşamkent 18/2", advert02));
        adverts.add(advert02);

        advertProductPackage.setAdverts(adverts);

        user.setAdvertProductPackage(advertProductPackage);

        CreditCard creditCard = new CreditCard();
        creditCard.setNameOnCreditCard("Cem Özer");
        creditCard.setCreditCardNumber("1111 2222 3333 7693");
        creditCard.setShortFormatExpirationYear("26");
        creditCard.setCVVnumber(234);
        creditCard.setUser(user);

        user.setCreditCard(creditCard);

        userRepository.save(user);
    }

    public void prepareDatabase03(){

        User user = new User();
        user.setPhone("+90 555 444 33 22");
        user.setName("Berna");
        user.setSurname("Erker");
        user.setEmail("bernaerker@gmail.com");
        user.setPassword("1997");

        AdvertProductPackage advertProductPackage = new AdvertProductPackage();
        advertProductPackage.setPackageExpirationDate(LocalDate.of(2022,2,10));
        advertProductPackage.setUser(user);


        List<Advert> adverts =  new ArrayList<>();

        Advert advert01 = new Advert();
        advert01.setAdvertType(AdvertType.FORSALE);
        advert01.setTitle("Yeni ev alacağımdan acil!!!");
        advert01.setDefinition("3+1 Merkezi 4.Kat");
        advert01.setAdvertStatus(AdvertStatus.ACTIVE);
        advert01.setAdvertProductPackage(advertProductPackage);
        advert01.setAddress(new Address(CountryType.ANKARA,"Çankaya","Tunalı 5/34", advert01));
        adverts.add(advert01);

        Advert advert02 = new Advert();
        advert02.setAdvertType(AdvertType.FORRENT);
        advert02.setTitle("Öğrenciye bekara ev.");
        advert02.setDefinition("3+1 Merkezi Isıtma Kotta");
        advert02.setAdvertStatus(AdvertStatus.ACTIVE);
        advert02.setAdvertProductPackage(advertProductPackage);
        advert02.setAddress(new Address(CountryType.ANKARA,"Yenimahelle","Çiğdem 16/8", advert02));
        adverts.add(advert02);

        Advert advert03 = new Advert();
        advert03.setAdvertType(AdvertType.FORSALE);
        advert03.setTitle("Sıfır ev");
        advert03.setDefinition("2+1 Pay Ölçerli Çatı Katı Şömineli");
        advert03.setAdvertStatus(AdvertStatus.ACTIVE);
        advert03.setAdvertProductPackage(advertProductPackage);
        advert03.setAddress(new Address(CountryType.ANKARA,"Çankaya","Esat 14/23", advert03));
        adverts.add(advert03);

        advertProductPackage.setAdverts(adverts);

        user.setAdvertProductPackage(advertProductPackage);

        CreditCard creditCard = new CreditCard();
        creditCard.setNameOnCreditCard("Berna Erker");
        creditCard.setCreditCardNumber("1111 2222 3333 3847");
        creditCard.setShortFormatExpirationYear("24");
        creditCard.setCVVnumber(456);
        creditCard.setUser(user);

        user.setCreditCard(creditCard);

        userRepository.save(user);
    }

    @Autowired
    InfoRepository infoRepository;

    public void prepareDatabaseInfo(){
        Info info = new Info("Price","95.50");
        infoRepository.save(info);
    }
}
