package com.example.emlakburada.queue;

import com.example.emlakburada.model.models.CreditCard;

public interface QueueService {
	
	void sendPayment(CreditCard creditCard, String price);

}
