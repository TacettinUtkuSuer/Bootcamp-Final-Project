package com.example.emlakburada.service;

import com.example.emlakburada.dto.request.AdvertRabbitMQRequest;
import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.dto.response.ProcessStatusResponse;
import com.example.emlakburada.exception.AdvertNotFoundException;
import com.example.emlakburada.exception.EmlakBuradaException;
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
            log.error(message);
            return new ProcessStatusResponse(false,message);
        }
        List<Advert> advertList = user.getAdvertProductPackage().getAdverts();
        if(advertList.size()>10){
            message = "You can create a maximum of 10 ads. therefore the record could not be created.";
            log.error(message);
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

        String message;

        List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();

        if(advertList==null){
            message = "Reading unsuccessful";
            log.error(message);
            throw new AdvertNotFoundException(message);
        }

        List<AdvertResponse> advertResponseList = convertFromAdvertListToAdvertResponseList(advertList);

        message = "Reading successful.";
        log.info(message);
        return advertResponseList;
    }


    public AdvertResponse readById(long userId, long advertId) {

        String message;

        Advert advert = advertRepository.getById(advertId);
        if(advert.getAdvertProductPackage().getUser().getId() == userId){
            message = "Reading successful.";
            log.info(message);
            return convertFromAdvertToAdvertResponse(advert);
        }
        message = "Reading unsuccessful";
        log.error(message);
        throw new AdvertNotFoundException(message);
    }


    public AdvertResponse updateById(long userId, long advertId, AdvertRequest advertRequest) {

        String message;

        if(userRepository.getById(userId).getAdvertProductPackage().getPackageExpirationDate().isBefore(LocalDate.now())){
            message = "The product package has expired.";
            log.error(message);
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

            message = "Advert has been updated.";
            log.info(message);
            return convertFromAdvertToAdvertResponse(advert);
        }

        message = "Advert could not be updated.";
        log.error("message");
        throw new AdvertNotFoundException(message);
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
        log.error(message);
        throw new AdvertNotFoundException(message);

    }

    public AdvertResponse activate(long userId, long advertId) {

        String message;

        long userIdFromAdvertId = advertRepository.getById(advertId).getAdvertProductPackage().getUser().getId();

        if(userIdFromAdvertId == userId){

            Advert advert = advertRepository.getById(advertId);
            advert.setAdvertStatus(AdvertStatus.ACTIVE);

            List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();

            advertList.set(advertList.indexOf(advertRepository.getById(advertId)), advert);

            User user = userRepository.getById(userId);
            user.getAdvertProductPackage().setAdverts(advertList);

            userRepository.save(user);

            message = "Advert has been activated.";
            log.info(message);
            return convertFromAdvertToAdvertResponse(advert);
        }
        message = "Advert could not been activated.";
        log.error(message);
        throw new EmlakBuradaException(message);
    }

    public AdvertResponse deactivate(long userId, long advertId) {

        String message;

        long userIdFromAdvertId = advertRepository.getById(advertId).getAdvertProductPackage().getUser().getId();

        if(userIdFromAdvertId == userId){

            Advert advert = advertRepository.getById(advertId);
            advert.setAdvertStatus(AdvertStatus.PASSIVE);

            List<Advert> advertList = userRepository.getById(userId).getAdvertProductPackage().getAdverts();

            advertList.set(advertList.indexOf(advertRepository.getById(advertId)), advert);

            User user = userRepository.getById(userId);
            user.getAdvertProductPackage().setAdverts(advertList);

            userRepository.save(user);

            message = "Advert has been deactivated.";
            log.info(message);
            return convertFromAdvertToAdvertResponse(advert);
        }

        message = "Advert could not been deactivated.";
        log.error(message);
        throw new EmlakBuradaException(message);
    }


    public List<AdvertResponse> readAllActive(long userId) {

        String message;

        List<AdvertResponse> advertResponseList = readAll(userId);

        if (advertResponseList==null) {
            message = "Reading unsuccessful";
            log.error(message);
            throw new AdvertNotFoundException(message);
        }

        List<AdvertResponse> advertResponseActiveList = new ArrayList<>();

        for (AdvertResponse advertResponse:advertResponseList) {
            if(advertResponse.getAdvertStatus() == AdvertStatus.ACTIVE){
                advertResponseActiveList.add(advertResponse);
            }
        }

        message = "Reading successful";
        log.info(message);
        return advertResponseActiveList;
    }

    public List<AdvertResponse> readAllPassive(long userId) {

        String message;

        List<AdvertResponse> advertResponseList = readAll(userId);

        if (advertResponseList==null) {
            message = "Reading unsuccessful";
            log.error(message);
            throw new AdvertNotFoundException(message);
        }

        List<AdvertResponse> advertResponsePassiveList = new ArrayList<>();

        for (AdvertResponse advertResponse:advertResponseList) {
            if(advertResponse.getAdvertStatus() == AdvertStatus.PASSIVE){
                advertResponsePassiveList.add(advertResponse);
            }
        }

        message = "Reading successful";
        log.info(message);
        return advertResponsePassiveList;
    }

    public List<AdvertResponse> readAllInreview(long userId) {

        String message;

        List<AdvertResponse> advertResponseList = readAll(userId);

        if (advertResponseList==null) {
            message = "Reading unsuccessful";
            log.error(message);
            throw new AdvertNotFoundException(message);
        }

        List<AdvertResponse> advertResponseInreviewList = new ArrayList<>();

        for (AdvertResponse advertResponse:advertResponseList) {
            if(advertResponse.getAdvertStatus() == AdvertStatus.IN_REVIEW){
                advertResponseInreviewList.add(advertResponse);
            }
        }

        message = "Reading successful";
        log.info(message);
        return advertResponseInreviewList;
    }
}
