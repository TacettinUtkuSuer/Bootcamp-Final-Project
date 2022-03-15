package com.example.emlakburada;

import com.example.emlakburada.model.models.User;
import com.example.emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmlakBuradaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaApplication.class, args);
    }

}
