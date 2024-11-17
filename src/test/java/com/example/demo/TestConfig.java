package com.example.demo;

import com.example.demo.service.PoolService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mockito.Mockito;

//@Configuration
public class TestConfig {

    @Bean
    public PoolService poolService() {
        return Mockito.mock(PoolService.class);
    }
}
