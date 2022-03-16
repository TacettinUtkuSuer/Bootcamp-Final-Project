package com.example.emlakburada.queue;

import com.example.emlakburada.config.RabbitMqConfig;
import emlakburada.config.RabbitMqConfig;
import emlakburada.service.EmailMessage;
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
	public void sendMessage(EmailMessage message) {
		rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), message);

	}

}
