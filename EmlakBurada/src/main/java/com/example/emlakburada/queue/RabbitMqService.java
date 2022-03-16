package com.example.emlakburada.queue;

import com.example.emlakburada.config.RabbitMqConfig;
import com.example.emlakburada.model.models.CreditCard;
import com.example.emlakburada.model.models.PaymentMessage;
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
	public void sendPayment(long creditCardId, String price) {
		PaymentMessage paymentMessage = new PaymentMessage(creditCardId, price);
		rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), paymentMessage);
	}

}
