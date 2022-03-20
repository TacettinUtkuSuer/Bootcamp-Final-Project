package com.example.emlakburadaadvertactivation.service;

import com.example.emlakburadaadvertactivation.dto.AdvertIdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMqListenerService {

    @Autowired
    StatusService statusService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(AdvertIdRequest advertIdRequest) throws MessagingException{
            if (statusService.advertReview(advertIdRequest.getAdvertId())) {
                log.info("RabbitMQ message has been taken.");
            }
    }

}
