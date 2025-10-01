package com.example.banksystem.repository;

import com.example.banksystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByCardNumberAndPin(String cardNumber, String pin); //Java8
}