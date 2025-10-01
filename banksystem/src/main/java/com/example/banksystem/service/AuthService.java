package com.example.banksystem.service;

import com.example.banksystem.model.User;
import com.example.banksystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Random;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;


    @Transactional
    public User signup(User user) {
        logger.debug("Starting signup process for user: {}", user.getName());

        try {
            Random ran = new Random(); //java Basics obj

            String formNo;
            do {  //control statements
                long first4 = ran.nextLong(9000L) + 1000L; // Java Basics
                formNo = String.valueOf(first4);
            } while (userRepository.existsById(formNo));


            String cardNumber;
            do {
                long first7 = (ran.nextLong() % 90000000L) + 1409963000000000L;
                cardNumber = String.valueOf(Math.abs(first7));
            } while (userRepository.findByCardNumberAndPin(cardNumber, null).isPresent());

            String pin = String.valueOf(Math.abs(ran.nextLong() % 9000L) + 1000L);

            user.setFormNo(formNo);
            user.setCardNumber(cardNumber);
            user.setPin(pin);

            User savedUser = userRepository.save(user);
            logger.info("User saved successfully with formNo: {}, cardNumber: {}",
                    savedUser.getFormNo(), savedUser.getCardNumber());

            return savedUser;
        } catch (Exception e) {
            logger.error("Failed to save user: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save user: " + e.getMessage(), e);
        }
    }


    public User login(String cardNumber, String pin) {
        logger.debug("Attempting login with cardNumber: {}", cardNumber);
        return userRepository.findByCardNumberAndPin(cardNumber, pin).orElse(null);
    }


    public void demonstrateGarbageCollection() {
        logger.debug("Starting garbage collection demonstration");

        Runtime runtime = Runtime.getRuntime();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        logger.debug("Memory used before GC: {} bytes", beforeMemory);

        // Create temporary objects to consume memory
        for (int i = 0; i < 1000; i++) {
            new String(new char[10000]);
        }

        System.gc();

        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        logger.debug("Memory used after GC: {} bytes", afterMemory);
    }
}
