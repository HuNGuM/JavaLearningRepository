package com.example.demo;

import com.example.demo.service.impl.PoolServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Iterator;
import com.example.demo.dto.PoolDTO;
import com.example.demo.entity.Pool;
import com.example.demo.mapper.PoolMapper;
import com.example.demo.repository.PoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest
class JavaLearningApplicationTests {
    @Mock
    private PoolRepository poolRepository;

    @Mock
    private PoolMapper poolMapper;

    @InjectMocks
    private PoolServiceImpl poolService;

    private PoolDTO poolDTO;
    private Pool pool;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        poolDTO = new PoolDTO();
        poolDTO.setName("Test Pool");
        poolDTO.setLocation("Test Location");
        poolDTO.setLanes(5);
        poolDTO.setSchedule("9 AM - 9 PM");

        pool = new Pool();
        pool.setName(poolDTO.getName());
        pool.setLocation(poolDTO.getLocation());
        pool.setLanes(poolDTO.getLanes());
        pool.setSchedule(poolDTO.getSchedule());
    }

    @Test
    void create() {
        when(poolMapper.toEntity(poolDTO)).thenReturn(pool);
        when(poolRepository.save(pool)).thenReturn(pool);
        when(poolMapper.toDTO(pool)).thenReturn(poolDTO);

        PoolDTO createdPool = poolService.create(poolDTO);

        assertEquals(poolDTO.getName(), createdPool.getName());
        verify(poolMapper).toEntity(poolDTO);
        verify(poolRepository).save(pool);
        verify(poolMapper).toDTO(pool);
    }
    @Test
    void update() {
        Long poolId = 1L;

        // Настраиваем моки для существующего пула
        when(poolRepository.findById(poolId)).thenReturn(Optional.of(pool));
        when(poolMapper.toEntity(poolDTO)).thenReturn(pool);
        when(poolRepository.save(pool)).thenReturn(pool);
        when(poolMapper.toDTO(pool)).thenReturn(poolDTO);

        // Обновляем пул
        PoolDTO updatedPool = poolService.update(poolDTO, poolId);

        // Проверяем, что обновленный пул соответствует ожидаемым данным
        assertEquals(poolDTO.getName(), updatedPool.getName());
        assertEquals(poolDTO.getLocation(), updatedPool.getLocation());
        assertEquals(poolDTO.getLanes(), updatedPool.getLanes());
        assertEquals(poolDTO.getSchedule(), updatedPool.getSchedule());

        // Проверяем взаимодействие с моками
        verify(poolRepository).findById(poolId);
        verify(poolMapper).toEntity(poolDTO);
        verify(poolRepository).save(pool);
        verify(poolMapper).toDTO(pool);
    }

    @Test
    void updatePoolNotFound() {
        Long poolId = 1L;
        when(poolRepository.findById(poolId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            poolService.update(poolDTO, poolId);
        });

        assertEquals("Not found pool with id: " + poolId, exception.getMessage());
    }


    @Test
    void delete() {
        Long poolId = 1L;
        when(poolRepository.existsById(poolId)).thenReturn(true);

        poolService.delete(poolId);

        verify(poolRepository).deleteById(poolId);
    }

    @Test
    void deletePoolNotFound() {
        Long poolId = 1L;
        when(poolRepository.existsById(poolId)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            poolService.delete(poolId);
        });

        assertEquals("Pool doesn't wxist with id: " + poolId, exception.getMessage());
    }

    @Test
    void findAll() {
        when(poolRepository.findAll()).thenReturn(Arrays.asList(pool));
        when(poolMapper.toDTO(pool)).thenReturn(poolDTO);

        List<PoolDTO> pools = poolService.findAll();

        assertEquals(1, pools.size());
        assertEquals(poolDTO.getName(), pools.get(0).getName());
        verify(poolRepository).findAll();
    }

    @Test
    void findById() {
        Long poolId = 1L;
        when(poolRepository.findById(poolId)).thenReturn(Optional.of(pool));
        when(poolMapper.toDTO(pool)).thenReturn(poolDTO);

        PoolDTO foundPool = poolService.findById(poolId);

        assertEquals(poolDTO.getName(), foundPool.getName());
        verify(poolRepository).findById(poolId);
    }

    @Test
    void findByIdNotFound() {
        Long poolId = 1L;
        when(poolRepository.findById(poolId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            poolService.findById(poolId);
        });

        assertEquals("Not found pool with id: " + poolId, exception.getMessage());
    }
}
