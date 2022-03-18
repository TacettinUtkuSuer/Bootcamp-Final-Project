package com.example.emlakburadapayment.queue;

import java.math.BigDecimal;

public interface QueueService {
	
	void sendAdvertStatusActivate(long creditCardId, BigDecimal price);

}
