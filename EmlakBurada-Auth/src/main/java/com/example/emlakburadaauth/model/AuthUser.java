package com.example.emlakburadaauth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
@Entity
public class AuthUser {
    @Id
    private Long id;
    private String email;
    private String password;
}
