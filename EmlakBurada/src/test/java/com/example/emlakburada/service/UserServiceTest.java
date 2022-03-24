package com.example.emlakburada.service;

import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.ProcessStatusResponse;
import com.example.emlakburada.exception.EmlakBuradaException;
import com.example.emlakburada.exception.UserNotFoundException;
import com.example.emlakburada.model.models.AdvertProductPackage;
import com.example.emlakburada.model.models.PaymentMessage;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.repository.InfoRepository;
import com.example.emlakburada.repository.UserRepository;
import com.example.emlakburada.utils.ModelGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    InfoRepository infoRepository;

    @Test
    public void pay_itShouldReturnError_whenUserIsNull() throws NullPointerException{
        // given
        User user = new User();
        // when

        // then
       assertThrows(UserNotFoundException.class,()->{userService.pay(1L);},"User not found.");

    }


}
