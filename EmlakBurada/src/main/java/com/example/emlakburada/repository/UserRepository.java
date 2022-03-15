package com.example.emlakburada.repository;

import com.example.emlakburada.model.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
