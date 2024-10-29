package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    @Test
    void passwordEncoder() {
        String encoded = new BCryptPasswordEncoder().encode("12345");
        System.out.println("encoded:" + encoded);
    }
}