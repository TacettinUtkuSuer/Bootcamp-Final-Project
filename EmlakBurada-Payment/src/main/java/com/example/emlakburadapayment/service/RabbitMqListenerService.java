package com.example.emlakburadapayment.service;

import com.example.emlakburadapayment.model.PaymentMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMqListenerService {


    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = "${emlakburada.rabbitmq.queue}")
    public void receiveMessage(PaymentMessage paymentMessage) throws MessagingException {
        paymentService.pay(paymentMessage.getCreditCardId(), paymentMessage.getPrice());
        log.info("RabbitMQ message has been taken.");
    }



}
