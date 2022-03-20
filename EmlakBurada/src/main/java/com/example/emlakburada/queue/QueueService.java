package com.example.emlakburada.queue;

import com.example.emlakburada.dto.request.AdvertRabbitMQRequest;

public interface QueueService {
	
	void sendMessageAndAdvertActivate(AdvertRabbitMQRequest advertIdRequest);

}
