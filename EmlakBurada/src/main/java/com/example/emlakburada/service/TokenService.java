package com.example.emlakburada.service;

import com.example.emlakburada.model.models.User;
import com.example.emlakburada.repository.UserRepository;
import com.example.emlakburada.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    public Long getUserIdByToken(String token){
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        String email = claims.getSubject();
        User user =  userRepository.findByEmail(email);
        return user.getId();
    }
}
