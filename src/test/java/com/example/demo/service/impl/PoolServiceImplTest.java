package com.example.demo.service.impl;

import com.example.demo.dto.PoolDTO;
import com.example.demo.repository.PoolRepository;
import com.example.demo.service.PoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PoolServiceImplTest {

    @Autowired
    private PoolService poolService;

    @Autowired
    private PoolRepository poolRepository;

    private PoolDTO poolDTO;

    @BeforeEach
    void setUp() {
        poolDTO = new PoolDTO();
        poolDTO.setName("Test Pool");
        poolDTO.setLocation("Location");
        poolDTO.setLanes(5);
        poolDTO.setSchedule("9:00 - 18:00");
    }

    @Test
    void testCreate() {
        PoolDTO createdPool = poolService.create(poolDTO);

        assertNotNull(createdPool);
        assertEquals("Test Pool", createdPool.getName());
        assertTrue(poolRepository.existsById(createdPool.getId()));
    }

    @Test
    void testUpdate() {
        PoolDTO createdPool = poolService.create(poolDTO);
        Long poolId = createdPool.getId();

        PoolDTO updateDTO = new PoolDTO();
        updateDTO.setName("Updated Pool");
        updateDTO.setLocation("Updated Location");
        updateDTO.setLanes(6);
        updateDTO.setSchedule("10:00 - 19:00");

        PoolDTO updatedPool = poolService.update(updateDTO, poolId);

        assertNotNull(updatedPool);
        assertEquals("Updated Pool", updatedPool.getName());
    }

    @Test
    void testDelete() {
        PoolDTO createdPool = poolService.create(poolDTO);
        Long poolId = createdPool.getId();

        poolService.delete(poolId);

        assertFalse(poolRepository.existsById(poolId));
    }

    @Test
    void testFindById() {
        PoolDTO createdPool = poolService.create(poolDTO);
        Long poolId = createdPool.getId();

        PoolDTO foundPool = poolService.findById(poolId);

        assertNotNull(foundPool);
        assertEquals("Test Pool", foundPool.getName());
    }

    @Test
    void testFindAll() {
        poolService.create(poolDTO);

        List<PoolDTO> pools = poolService.findAll();

        assertNotNull(pools);
        assertEquals(1, pools.size());
    }
}