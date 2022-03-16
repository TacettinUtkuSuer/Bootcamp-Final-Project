package com.example.emlakburada.queue;

import com.example.emlakburada.config.RabbitMqConfig;
import com.example.emlakburada.model.models.CreditCard;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RabbitMqService implements QueueService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMqConfig config;

	@Override
	public void sendPayment(CreditCard creditCard, String price) {
		Map<CreditCard,String> data = new HashMap();
		data.put(creditCard,price);
		rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), data);
	}

}
