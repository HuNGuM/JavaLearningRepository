package com.example.demo.service.impl;
import com.example.demo.dto.PoolDTO;
import com.example.demo.entity.Pool;
import com.example.demo.mapper.PoolMapper;
import com.example.demo.repository.PoolRepository;
import com.example.demo.service.PoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({PoolServiceImpl.class, PoolMapper.class})
public class PoolServiceImplTest {

    @Autowired
    private PoolServiceImpl poolService;

    @Autowired
    private PoolRepository poolRepository;

    @Autowired
    private PoolMapper poolMapper;
    @BeforeEach
    void setup() {
        // Очистить базу перед каждым тестом
        poolRepository.deleteAll();

        // Добавить несколько записей для тестов
        poolRepository.save(new Pool(null, "Pool A", "Location A", 5, "8:00-20:00"));
        poolRepository.save(new Pool(null, "Pool B", "Location B", 10, "9:00-22:00"));
    }

    @Test
    void createPool_shouldAddNewPool() {
        PoolDTO newPool = new PoolDTO(null, "Pool C", "Location C", 8, "10:00-18:00");

        PoolDTO createdPool = poolService.create(newPool);

        assertThat(createdPool.getId()).isNotNull();
        assertThat(createdPool.getName()).isEqualTo("Pool C");
        assertThat(createdPool.getLocation()).isEqualTo("Location C");
        assertThat(createdPool.getLanes()).isEqualTo(8);

        List<Pool> allPools = poolRepository.findAll();
        assertThat(allPools).hasSize(3);
    }
}