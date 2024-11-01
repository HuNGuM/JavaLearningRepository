package com.example.demo.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Sha256PasswordEncoderTest {
    @Test
    public void returnTest(){
        String rawPassword = "adminadmin"; // пароль для шифрования
        Sha256PasswordEncoder encoder = new Sha256PasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword); // Вывод закодированного пароля
    }


}
