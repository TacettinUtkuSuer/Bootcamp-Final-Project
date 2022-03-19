package com.example.emlakburada.service;

import com.example.emlakburada.model.models.CreditCard;
import com.example.emlakburada.model.models.Info;
import com.example.emlakburada.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  /*  @Autowired
    InfoRepository infoRepository;

    public boolean pay(){
        Info priceInfo = infoRepository.findByKey("price");
        if (priceInfo==null){
            return false;
        }
        System.out.println(priceInfo.getValue());
        return true;
    }*/
}
