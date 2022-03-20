package com.example.emlakburada.queue;

import com.example.emlakburada.config.RabbitMqConfig;
import com.example.emlakburada.dto.request.AdvertRabbitMQRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements QueueService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMqConfig config;

	@Override
	public void sendMessageAndAdvertActivate(AdvertRabbitMQRequest advertIdRequest) {
		rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), advertIdRequest);
	}

}
