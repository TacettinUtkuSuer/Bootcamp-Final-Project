package com.example.emlakburadaadvertactivation.repository;

import com.example.emlakburadaadvertactivation.model.models.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

    Advert findAdvertbyId(long id);
}
