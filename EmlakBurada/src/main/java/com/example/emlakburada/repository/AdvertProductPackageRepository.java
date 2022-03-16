package com.example.emlakburada.repository;

import com.example.emlakburada.model.models.AdvertProductPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertProductPackageRepository extends JpaRepository<AdvertProductPackage, Long> {
}
