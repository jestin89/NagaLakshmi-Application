package com.nagas.backend.repository;

import com.nagas.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findByUserId(Integer id);
}
