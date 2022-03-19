package com.example.emlakburada.service;

import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.models.Advert;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.repository.AdvertRepository;
import com.example.emlakburada.repository.UserRepository;
import com.example.emlakburada.service.baseServices.AdvertBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertService extends AdvertBaseService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdvertRepository advertRepository;

    public String create(long userId, AdvertRequest advertRequest) {
        User user = userRepository.getById(userId);
        List<Advert> advertList = user.getAdvertProductPackage().getAdverts();
        if(advertList.size()>10){
            return new String("You can create a maximum of 10 ads. therefore the record could not be created.");
        }
        Advert advert = new Advert();
        advert = convertFromAdvertRequestToAdvert(userId, advertRequest);
        advertList.add(advert);
        user.getAdvertProductPackage().setAdverts(advertList);
        userRepository.save(user);


        // BURAYA ASENKRON YAPI KOYULACAK



        return new String("Advert has been created.");
    }


    public List<AdvertResponse> readAll(long userId) {

        List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();
        List<AdvertResponse> advertResponseList = convertFromAdvertListToAdvertResponseList(advertList);

        return advertResponseList;
    }


    public AdvertResponse readById(long userId, long advertId) {

        Advert advert = advertRepository.getById(advertId);
        if(advert.getAdvertProductPackage().getUser().getId() == userId){
            return convertFromAdvertToAdvertResponse(advert);
        }
        return null;
    }


    public AdvertResponse updateById(long userId, long advertId, AdvertRequest advertRequest) {

        long userIdFromAdvertId = advertRepository.getById(advertId).getAdvertProductPackage().getUser().getId();

        if(userIdFromAdvertId == userId){

            Advert advert = convertFromAdvertRequestToAdvert(userId, advertRequest);
            advert.setId(advertId);
            advert.getAddress().setId(advertRepository.getById(advertId).getAddress().getId());

            List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();

            advertList.set(advertList.indexOf(advertRepository.getById(advertId)), advert);

            User user = userRepository.getById(userId);
            user.getAdvertProductPackage().setAdverts(advertList);


            userRepository.save(user);


            // BURAYA ASENKRON YAPI KOYULACAK



            return convertFromAdvertToAdvertResponse(advert);
        }

        return null;
    }

    public String deleteById(long userId, long advertId) {

        long userIdFromAdvertId = advertRepository.getById(advertId).getAdvertProductPackage().getUser().getId();

        if(userIdFromAdvertId == userId){

            List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();

            advertList.remove(advertList.indexOf(advertRepository.getById(advertId)));

            User user = userRepository.getById(userId);
            user.getAdvertProductPackage().setAdverts(advertList);


            userRepository.save(user);

            return new String("The ad has been deleted.");
        }

        return new String("Advert could not be deleted.");

    }

    public AdvertResponse activate(long userId, long advertId) {

        long userIdFromAdvertId = advertRepository.getById(advertId).getAdvertProductPackage().getUser().getId();

        if(userIdFromAdvertId == userId){

            Advert advert = advertRepository.getById(advertId);
            advert.setAdvertStatus(AdvertStatus.ACTIVE);

            List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();

            advertList.set(advertList.indexOf(advertRepository.getById(advertId)), advert);

            User user = userRepository.getById(userId);
            user.getAdvertProductPackage().setAdverts(advertList);

            userRepository.save(user);

            return convertFromAdvertToAdvertResponse(advert);
        }

        return null;
    }

    public AdvertResponse deactivate(long userId, long advertId) {

        long userIdFromAdvertId = advertRepository.getById(advertId).getAdvertProductPackage().getUser().getId();

        if(userIdFromAdvertId == userId){

            Advert advert = advertRepository.getById(advertId);
            advert.setAdvertStatus(AdvertStatus.PASSIVE);

            List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();

            advertList.set(advertList.indexOf(advertRepository.getById(advertId)), advert);

            User user = userRepository.getById(userId);
            user.getAdvertProductPackage().setAdverts(advertList);

            userRepository.save(user);

            return convertFromAdvertToAdvertResponse(advert);
        }

        return null;
    }


    public List<AdvertResponse> readAllActive(long userId) {

        List<AdvertResponse> advertResponseList = readAll(userId);

        List<AdvertResponse> advertResponseActiveList = new ArrayList<>();

        for (AdvertResponse advertResponse:advertResponseList) {
            if(advertResponse.getAdvertStatus() == AdvertStatus.ACTIVE){
                advertResponseActiveList.add(advertResponse);
            }
        }

        return advertResponseActiveList;
    }

    public List<AdvertResponse> readAllPassive(long userId) {

        List<AdvertResponse> advertResponseList = readAll(userId);

        List<AdvertResponse> advertResponsePassiveList = new ArrayList<>();

        for (AdvertResponse advertResponse:advertResponseList) {
            if(advertResponse.getAdvertStatus() == AdvertStatus.PASSIVE){
                advertResponsePassiveList.add(advertResponse);
            }
        }

        return advertResponsePassiveList;
    }

    public List<AdvertResponse> readAllInreview(long userId) {

        List<AdvertResponse> advertResponseList = readAll(userId);

        List<AdvertResponse> advertResponseInreviewList = new ArrayList<>();

        for (AdvertResponse advertResponse:advertResponseList) {
            if(advertResponse.getAdvertStatus() == AdvertStatus.IN_REVIEW){
                advertResponseInreviewList.add(advertResponse);
            }
        }

        return advertResponseInreviewList;
    }
}
