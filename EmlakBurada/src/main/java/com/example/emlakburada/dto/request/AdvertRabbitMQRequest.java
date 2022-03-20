package com.example.emlakburada.dto.request;

import com.example.emlakburada.model.enums.AdvertType;
import com.example.emlakburada.model.models.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertRabbitMQRequest {

    private long advertId;

}