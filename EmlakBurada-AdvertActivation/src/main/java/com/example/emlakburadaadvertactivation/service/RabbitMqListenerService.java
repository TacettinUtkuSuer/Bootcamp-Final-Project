package com.example.emlakburadaadvertactivation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqListenerService {


    @Autowired
    private StatusService statusService;


    @RabbitListener(queues = "${emlakburada.rabbitmq.queue}")
    public void receiveMessage(long advertId) throws MessagingException{

            if (statusService.advertReview(advertId)) {
                log.info("RabbitMQ message has been taken.");
            }
    }

}
