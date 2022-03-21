package com.example.emlakburada.controller;

import com.example.emlakburada.dto.request.AdvertRabbitMQRequest;
import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.dto.response.ProcessStatusResponse;
import com.example.emlakburada.queue.RabbitMqService;
import com.example.emlakburada.service.AdvertService;
import com.example.emlakburada.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AdvertController {

    @Autowired
    AdvertService advertService;

    @Autowired
    TokenService tokenService;

    @Autowired
    RabbitMqService rabbitMqService;


    @PostMapping(value = "/adverts/create")
    public ResponseEntity<ProcessStatusResponse> create(@RequestBody AdvertRequest advertRequest, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        ProcessStatusResponse processStatusResponse = advertService.create(userId, advertRequest);
        return new ResponseEntity<>(processStatusResponse, HttpStatus.CREATED);
    }


    @GetMapping(value = "/adverts/read/{advertId}")
    public ResponseEntity<AdvertResponse> read(@PathVariable(required = false) long advertId, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        AdvertResponse advertResponse = advertService.readById(userId, advertId);
        return new ResponseEntity<>(advertResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/adverts/update/{advertId}")
    public ResponseEntity<AdvertResponse> update(@PathVariable(required = false) long advertId, @RequestBody AdvertRequest advertRequest, @RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        AdvertResponse advertResponse = advertService.updateById(userId, advertId, advertRequest);
        return new ResponseEntity<>(advertResponse, HttpStatus.CREATED);
    }


    @PostMapping(value = "/adverts/delete/{advertId}")
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

    @GetMapping(value = "/adverts/read/active")
    public ResponseEntity<List<AdvertResponse>> readActive(@RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        List<AdvertResponse> advertReponseList = advertService.readAllActive(userId);
        return new ResponseEntity<>(advertReponseList, HttpStatus.OK);
    }


    @GetMapping(value = "/adverts/read/passive")
    public ResponseEntity<List<AdvertResponse>> readPassive(@RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        List<AdvertResponse> advertReponseList = advertService.readAllPassive(userId);
        return new ResponseEntity<>(advertReponseList, HttpStatus.OK);
    }


    @GetMapping(value = "/adverts/read/inreview")
    public ResponseEntity<List<AdvertResponse>> readInreview(@RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        List<AdvertResponse> advertReponseList = advertService.readAllInreview(userId);
        return new ResponseEntity<>(advertReponseList, HttpStatus.OK);
    }


    @GetMapping(value = "/adverts/read/all")
    public ResponseEntity<List<AdvertResponse>> readAll(@RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);
        List<AdvertResponse> advertReponseList = advertService.readAll(userId);
        return new ResponseEntity<>(advertReponseList, HttpStatus.OK);
    }


    //------------------------------------------------------------------------------------------------------------------




}
