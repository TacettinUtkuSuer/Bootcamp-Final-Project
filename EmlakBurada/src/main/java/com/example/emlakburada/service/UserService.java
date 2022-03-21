package com.example.emlakburada.service;

import com.example.emlakburada.dto.response.ProcessStatusResponse;
import com.example.emlakburada.model.models.PaymentMessage;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.repository.InfoRepository;
import com.example.emlakburada.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InfoRepository infoRepository;


    public ProcessStatusResponse pay(long userId){

        User user = userRepository.getById(userId);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9092/payandsavepayment";

        PaymentMessage paymentMessage = new PaymentMessage(user.getCreditCard().getId(), new BigDecimal(infoRepository.findByKey("Price").getValue()));
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url,paymentMessage,Boolean.class);

        String message;
        if (responseEntity.getBody()){
            LocalDate localDate = user.getAdvertProductPackage().getPackageExpirationDate();

            if(localDate.isAfter(LocalDate.now())){
                user.getAdvertProductPackage().setPackageExpirationDate(user.getAdvertProductPackage().getPackageExpirationDate().plusDays(30));
            } else {
                user.getAdvertProductPackage().setPackageExpirationDate(LocalDate.now().plusDays(30));
            }

            userRepository.save(user);
            message = "The payment is successful.";
            log.info(message);
            return new ProcessStatusResponse(true, message);
        } else {
            message = "There was a problem with the payment.";
            log.warn(message);
            return new ProcessStatusResponse(false,message);
        }
    }
}
