package com.example.emlakburada.repository;

import com.example.emlakburada.model.models.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {

    Info findByKey(String key);

}
