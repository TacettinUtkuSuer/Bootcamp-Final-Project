package com.example.emlakburadaauth.service;

import com.example.emlakburadaauth.dto.AuthUserRequest;
import com.example.emlakburadaauth.dto.AuthUserResponse;
import com.example.emlakburadaauth.model.AuthUser;
import com.example.emlakburadaauth.repository.AuthUserRepository;
import com.example.emlakburadaauth.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

@SpringBootTest
public class AuthUserServiceTest {
    @InjectMocks
    AuthUserService authUserService;

    @Mock
    AuthUserRepository authUserRepository;

    @Mock
    JwtUtil jwtUtil;

    @Test
    public void getTokenTest_UserIsNull(){
        //given
        AuthUserRequest request = new AuthUserRequest("email","password");
        Optional<AuthUser> user = Optional.empty();
        //when
        Mockito.when(authUserRepository.findByEmail(request.getEmail())).thenReturn(user);
        //then
        assertThrows(Exception.class,()->{authUserService.getToken(request);},"There is no such e-mail.");
    }

    @Test
    public void getTokenTest_UserIsNotNull(){
        //given
        AuthUserRequest request = new AuthUserRequest("email","password");
        AuthUser user = new AuthUser();
        user.setEmail("mail");
        user.setPassword("password2");
        Optional<AuthUser> userOpt = Optional.of(user);
        //when
        Mockito.when(authUserRepository.findByEmail(request.getEmail())).thenReturn(userOpt);
        //then
        assertThrows(Exception.class,()->{authUserService.getToken(request);},"User's password not valid");
    }

    @Test
    public void getTokenTest_createToken() throws Exception{
        //given
        AuthUserRequest request = new AuthUserRequest("email","password");
        AuthUser user = new AuthUser();
        user.setEmail("mail");
        user.setPassword("password");
        Optional<AuthUser> userOpt = Optional.of(user);
        AuthUserResponse response = new AuthUserResponse("token");
        //when
        Mockito.when(authUserRepository.findByEmail(request.getEmail())).thenReturn(userOpt);
        Mockito.when(jwtUtil.generateToken(userOpt.get())).thenReturn("token");
        //then
        assertEquals(response.getClass(),authUserService.getToken(request).getClass());
    }

}
