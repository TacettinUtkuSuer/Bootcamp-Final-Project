package com.example.emlakburada.queue;

import com.example.emlakburada.model.models.CreditCard;

import java.math.BigDecimal;

public interface QueueService {
	
	void sendPayment(long creditCardId, BigDecimal price);

}
