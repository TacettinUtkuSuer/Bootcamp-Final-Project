package com.example.emlakburadaauth.service;

import org.springframework.stereotype.Service;

import com.example.emlakburadaauth.dto.AuthUserRequest;
import com.example.emlakburadaauth.dto.AuthUserResponse;
import com.example.emlakburadaauth.model.AuthUser;
import com.example.emlakburadaauth.repository.AuthUserRepository;
import com.example.emlakburadaauth.util.JwtUtil;
import com.example.emlakburadaauth.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;

    private final JwtUtil jwtUtil;

    public AuthUserResponse getToken(AuthUserRequest request) throws Exception {
        Optional<AuthUser> userOpt = authUserRepository.findByEmail(request.getEmail());

        if(userOpt.isPresent()) {

            if (!UserUtil.isValidPassword(userOpt.get().getPassword(), request.getPassword())) {
                log.error("User's password not valid " + request.getEmail());
                throw new Exception("User's password not valid");
            }

        } else {

            throw new Exception("There is no such e-mail.");

        }

        return new AuthUserResponse(jwtUtil.generateToken(userOpt.get()));
    }

}