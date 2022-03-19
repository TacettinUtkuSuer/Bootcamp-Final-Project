package com.example.emlakburada.service.baseServices;

import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.AddressResponse;
import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.enums.AdvertType;
import com.example.emlakburada.model.enums.CountryType;
import com.example.emlakburada.model.models.Address;
import com.example.emlakburada.model.models.Advert;
import com.example.emlakburada.model.models.AdvertProductPackage;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertBaseService {

    @Autowired
    UserRepository userRepository;

    public Advert convertFromAdvertRequestToAdvert(long userId, AdvertRequest advertRequest) {

        Advert advert = new Advert();
        advert.setAdvertType(advertRequest.getAdvertType());
        advert.setTitle(advertRequest.getTitle());
        advert.setDefinition(advertRequest.getDefinition());
        advert.setAdvertStatus(AdvertStatus.IN_REVIEW);

        User user = userRepository.getById(userId);
        advert.setAdvertProductPackage(user.getAdvertProductPackage());

        Address address = new Address(advertRequest.getAddress().getCountry(),advertRequest.getAddress().getDistrict(),advertRequest.getAddress().getFullAddress(), advert);

        advert.setAddress(address);

        return advert;
    }

    public List<AdvertResponse> convertFromAdvertListToAdvertResponseList(List<Advert> advertList) {

        List<AdvertResponse> advertResponseList = new ArrayList<>();

        for (Advert advert : advertList) {
            AdvertResponse advertResponse = new AdvertResponse();

            advertResponse.setId(advert.getId());
            advertResponse.setAdvertType(advert.getAdvertType());
            advertResponse.setTitle(advert.getTitle());
            advertResponse.setDefinition(advert.getDefinition());
            advertResponse.setAdvertStatus(advert.getAdvertStatus());

            AddressResponse addressResponse = new AddressResponse();

            addressResponse.setId( advert.getAddress().getId());
            addressResponse.setCountry( advert.getAddress().getCountry());
            addressResponse.setDistrict( advert.getAddress().getDistrict());
            addressResponse.setFullAddress( advert.getAddress().getFullAddress());

            advertResponse.setAddress(addressResponse);

            advertResponseList.add(advertResponse);
        }
        return advertResponseList;
    }

    public AdvertResponse convertFromAdvertToAdvertResponse(Advert advert) {

        AdvertResponse advertResponse = new AdvertResponse();

        advertResponse.setId(advert.getId());
        advertResponse.setAdvertType(advert.getAdvertType());
        advertResponse.setTitle(advert.getTitle());
        advertResponse.setDefinition(advert.getDefinition());
        advertResponse.setAdvertStatus(advert.getAdvertStatus());

        AddressResponse addressResponse = new AddressResponse();

        addressResponse.setId( advert.getAddress().getId());
        addressResponse.setCountry( advert.getAddress().getCountry());
        addressResponse.setDistrict( advert.getAddress().getDistrict());
        addressResponse.setFullAddress( advert.getAddress().getFullAddress());

        advertResponse.setAddress(addressResponse);

        return advertResponse;
    }

}
