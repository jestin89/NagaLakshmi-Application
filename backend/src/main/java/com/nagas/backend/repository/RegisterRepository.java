package com.nagas.backend.repository;

import com.nagas.backend.entity.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<UserRegister, Integer> {
    UserRegister findByUserName(String userName);
}
