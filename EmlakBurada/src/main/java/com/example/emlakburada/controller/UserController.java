package com.example.emlakburada.controller;

import com.example.emlakburada.dto.response.ProcessStatusResponse;
import com.example.emlakburada.service.PrepareDatabaseService;
import com.example.emlakburada.service.TokenService;
import com.example.emlakburada.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping(value = "/pay")
    public ResponseEntity<ProcessStatusResponse> pay(@RequestHeader(value="Authorization") String token){
        long userId = tokenService.getUserIdByToken(token);

        ProcessStatusResponse processStatusResponse = userService.pay(userId);

        return new ResponseEntity<>(processStatusResponse, HttpStatus.OK);
    }

    @Autowired
    PrepareDatabaseService prepareDatabaseService;

    @PostMapping(value = "/prepare")
    public ResponseEntity<HttpStatus> prepare(){

        prepareDatabaseService.prepareDatabase01();
        prepareDatabaseService.prepareDatabase02();
        prepareDatabaseService.prepareDatabase03();
        prepareDatabaseService.prepareDatabaseInfo();

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
