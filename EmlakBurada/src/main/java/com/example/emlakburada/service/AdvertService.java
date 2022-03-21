package com.example.emlakburada.service;

import com.example.emlakburada.dto.request.AdvertRabbitMQRequest;
import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.dto.response.ProcessStatusResponse;
import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.models.Advert;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.queue.RabbitMqService;
import com.example.emlakburada.repository.AdvertRepository;
import com.example.emlakburada.repository.UserRepository;
import com.example.emlakburada.service.baseServices.AdvertBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdvertService extends AdvertBaseService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdvertRepository advertRepository;

    @Autowired
    RabbitMqService rabbitMqService;

    public ProcessStatusResponse create(long userId, AdvertRequest advertRequest) {

        String message;

        User user = userRepository.getById(userId);
        if(user.getAdvertProductPackage().getPackageExpirationDate().isBefore(LocalDate.now())){
            message = "Your advert package has expired. Please buy new package.";
            log.warn(message);
            return new ProcessStatusResponse(false,message);
        }
        List<Advert> advertList = user.getAdvertProductPackage().getAdverts();
        if(advertList.size()>10){
            message = "You can create a maximum of 10 ads. therefore the record could not be created.";
            log.warn(message);
            return new ProcessStatusResponse(false, message);
        }
        Advert advert = new Advert();
        advert = convertFromAdvertRequestToAdvert(userId, advertRequest);
        advertList.add(advert);
        user.getAdvertProductPackage().setAdverts(advertList);
        userRepository.save(user);

        AdvertRabbitMQRequest advertRabbitMQRequest = new AdvertRabbitMQRequest(user.getAdvertProductPackage().getAdverts().get(advertList.size()-1).getId());
        rabbitMqService.sendMessageAndAdvertActivate(advertRabbitMQRequest);

        message = "Advert has been created.";
        log.info(message);
        return new ProcessStatusResponse(true,message);
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

        if(userRepository.getById(userId).getAdvertProductPackage().getPackageExpirationDate().isBefore(LocalDate.now())){
            return null;
        }

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

            AdvertRabbitMQRequest advertRabbitMQRequest = new AdvertRabbitMQRequest(advertId);
            rabbitMqService.sendMessageAndAdvertActivate(advertRabbitMQRequest);

            return convertFromAdvertToAdvertResponse(advert);
        }

        return null;
    }

    public ProcessStatusResponse deleteById(long userId, long advertId) {

        long userIdFromAdvertId = advertRepository.getById(advertId).getAdvertProductPackage().getUser().getId();

        String message;
        if(userIdFromAdvertId == userId){

            List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();

            advertList.remove(advertList.indexOf(advertRepository.getById(advertId)));

            User user = userRepository.getById(userId);
            user.getAdvertProductPackage().setAdverts(advertList);


            userRepository.save(user);

            message="The ad has been deleted.";
            log.info(message);
            return new ProcessStatusResponse(true,message);
        }

        message="Advert could not be deleted.";
        log.warn(message);
        return new ProcessStatusResponse(false,message);

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
