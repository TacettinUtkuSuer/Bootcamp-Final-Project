package com.example.emlakburada.controller;

import com.example.emlakburada.repository.UserRepository;
import com.example.emlakburada.service.TokenService;
import com.example.emlakburada.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@RestController
public class denemeController {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/gettoken")
    public ResponseEntity<String> gets(@RequestHeader(value="Authorization") String token){


        return new ResponseEntity<>(userRepository.getById( tokenService.getUserIdByToken(token) ).getName(), HttpStatus.OK);
    }
}
