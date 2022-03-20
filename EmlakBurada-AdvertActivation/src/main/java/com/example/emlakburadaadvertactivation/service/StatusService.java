package com.example.emlakburadaadvertactivation.service;

import com.example.emlakburadaadvertactivation.model.enums.AdvertStatus;
import com.example.emlakburadaadvertactivation.model.models.Advert;
import com.example.emlakburadaadvertactivation.repository.AdvertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class StatusService {

    @Autowired
    AdvertRepository advertRepository;

    public boolean advertReview(long advertId){
        // Normally, in this section, the advert will be examined and their publicationability will be cheched.
        boolean publicationDecision = true;
        if (publicationDecision) {
            return advertPublicate(advertId);
        } else {
            return false;
        }

    }

    public boolean advertPublicate(long advertId){
        try {

            Optional<Advert> advertOpt =  advertRepository.findById(advertId);

            if(advertOpt.isPresent()){

                Advert advert = advertOpt.get();
                advert.setAdvertStatus(AdvertStatus.ACTIVE);
                advertRepository.save(advert);
                log.info("Saved");
                return true;

            } else {
                return false;
            }

        } catch(Exception e) {
            log.warn(e.getMessage());
            return false;
        }

    }

}
