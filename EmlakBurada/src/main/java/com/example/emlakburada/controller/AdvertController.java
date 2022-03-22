package com.example.emlakburada.controller;

import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.dto.response.ProcessStatusResponse;
import com.example.emlakburada.exception.EmlakBuradaException;
import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.service.AdvertService;
import com.example.emlakburada.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AdvertController {

    private final AdvertService advertService;

    private final TokenService tokenService;

    @PostMapping(value = "/adverts")
    public ResponseEntity<ProcessStatusResponse> create(@RequestBody AdvertRequest advertRequest, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        ProcessStatusResponse processStatusResponse = advertService.create(userId, advertRequest);
        return new ResponseEntity<>(processStatusResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/adverts/{advertId}")
    public ResponseEntity<AdvertResponse> read(@PathVariable(required = false) long advertId, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        AdvertResponse advertResponse = advertService.readById(userId, advertId);
        return new ResponseEntity<>(advertResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/adverts/{advertId}")
    public ResponseEntity<AdvertResponse> update(@PathVariable(required = false) long advertId, @RequestBody AdvertRequest advertRequest, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        AdvertResponse advertResponse = advertService.updateById(userId, advertId, advertRequest);
        return new ResponseEntity<>(advertResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/adverts/{advertId}")
    public ResponseEntity<ProcessStatusResponse> delete(@PathVariable(required = false) long advertId, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        ProcessStatusResponse processStatusResponse = advertService.deleteById(userId, advertId);
        return new ResponseEntity<>(processStatusResponse, HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------------------------

    @PostMapping(value = "/adverts/{advertId}/active")
    public ResponseEntity<AdvertResponse> activate(@PathVariable(required = false) long advertId, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        AdvertResponse advertResponse = advertService.activate(userId, advertId);
        return new ResponseEntity<>(advertResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/adverts/{advertId}/passive")
    public ResponseEntity<AdvertResponse> deactivate(@PathVariable(required = false) long advertId, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        AdvertResponse advertResponse = advertService.deactivate(userId, advertId);
        return new ResponseEntity<>(advertResponse, HttpStatus.CREATED);
    }

    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(value = "/adverts/read/{status}")
    public ResponseEntity<List<AdvertResponse>> readActive(@PathVariable(required = false) String status, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        AdvertStatus advertStatus = AdvertStatus.value(status);
        if(advertStatus == null){
            throw new EmlakBuradaException("Advert Status not found.");
        }
        List<AdvertResponse> advertReponseList = advertService.readAllByStatus(userId, advertStatus);
        return new ResponseEntity<>(advertReponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/adverts")
    public ResponseEntity<List<AdvertResponse>> readAll(@RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        List<AdvertResponse> advertReponseList = advertService.readAll(userId);
        return new ResponseEntity<>(advertReponseList, HttpStatus.OK);
    }

}
