package com.example.emlakburadaauth.controller;

import com.example.emlakburadaauth.dto.AuthUserRequest;
import com.example.emlakburadaauth.dto.AuthUserResponse;
import com.example.emlakburadaauth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @PostMapping(value = "/auth")
    public ResponseEntity<AuthUserResponse> getToken(@RequestBody AuthUserRequest request) throws Exception {
        return new ResponseEntity<>(authUserService.getToken(request), HttpStatus.OK);
    }
}
