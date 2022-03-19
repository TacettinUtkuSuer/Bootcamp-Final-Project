package com.example.emlakburada.dto.request;

import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.enums.AdvertType;
import com.example.emlakburada.model.models.Address;
import com.example.emlakburada.model.models.AdvertProductPackage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertRequest {

    private AdvertType advertType;
    private String title;
    private String definition;
    private Address address;

}