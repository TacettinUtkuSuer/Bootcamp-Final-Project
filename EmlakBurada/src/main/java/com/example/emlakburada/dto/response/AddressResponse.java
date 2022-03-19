package com.example.emlakburada.dto.response;

import com.example.emlakburada.model.enums.CountryType;
import com.example.emlakburada.model.models.Advert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private Long id;
    private CountryType country;
    private String district;
    private String fullAddress;

}
