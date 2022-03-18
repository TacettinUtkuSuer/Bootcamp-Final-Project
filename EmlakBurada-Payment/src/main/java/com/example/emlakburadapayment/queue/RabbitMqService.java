package com.example.emlakburadapayment.queue;

import com.example.emlakburadapayment.config.RabbitMqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RabbitMqService implements QueueService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMqConfig config;

	@Override
	public void sendAdvertStatusActivate(long creditCardId, BigDecimal price) {
		// PaymentMessage paymentMessage = new PaymentMessage(creditCardId, price);
		// rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), paymentMessage);
	}

}
