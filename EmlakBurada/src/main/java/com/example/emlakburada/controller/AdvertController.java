package com.example.emlakburada.controller;

import com.example.emlakburada.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdvertController {
    /*
    @Autowired
    AdvertService advertService;

    @PostMapping(value = "/create")
    public ResponseEntity<AdvertResponse> create(@RequestBody AdvertRequest advertRequest){
        return ResponseEntity<>(advertReponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readAll")
    public ResponseEntity<List<AdvertResponse>> readAll(){
        return ResponseEntity<>(advertReponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/read/{advertId}")
    public ResponseEntity<AdvertResponse> read(@PathVariable(required = false) long advertId){
        return ResponseEntity<>(advertReponse, HttpStatus.OK);
    }

    @PostMapping(value = "/update/{advertId}")
    public ResponseEntity<AdvertResponse> update(@PathVariable(required = false) long advertId, @RequestBody AdvertRequest advertRequest){
        return ResponseEntity<>(advertReponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/delete/{advertId}")
    public ResponseEntity<String> delete(@PathVariable(required = false) long advertId){
        return ResponseEntity<>(message, HttpStatus.OK);
    }

     */
}
