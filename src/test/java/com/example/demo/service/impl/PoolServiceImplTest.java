package com.example.demo.service.impl;
import com.example.demo.dto.PoolDTO;
import com.example.demo.entity.Pool;
import com.example.demo.mapper.PoolMapper;
import com.example.demo.repository.PoolRepository;
import com.example.demo.service.PoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PoolServiceImplTest {

    @Mock
    private PoolRepository poolRepository;

    @Mock
    private PoolMapper poolMapper;

    @InjectMocks
    private PoolServiceImpl poolService;

    private Pool pool;
    private PoolDTO poolDTO;

    @BeforeEach
    void setUp() {
        pool = new Pool(1L, "Pool 1", "Location 1", 5, "Schedule 1");
        poolDTO = new PoolDTO(1L, "Pool 1", "Location 1", 5, "Schedule 1");
    }

    @Test
    void testCreatePool() {
        when(poolMapper.toEntity(poolDTO)).thenReturn(pool);
        when(poolRepository.save(pool)).thenReturn(pool);
        when(poolMapper.toDTO(pool)).thenReturn(poolDTO);

        PoolDTO result = poolService.create(poolDTO);

        assertNotNull(result);
        assertEquals(poolDTO.getName(), result.getName());
        verify(poolRepository, times(1)).save(pool);
        verify(poolMapper, times(1)).toDTO(pool);
    }

    @Test
    void testUpdatePool() {
        when(poolRepository.findById(1L)).thenReturn(Optional.of(pool));
        when(poolRepository.save(pool)).thenReturn(pool);
        when(poolMapper.toDTO(pool)).thenReturn(poolDTO);

        PoolDTO result = poolService.update(poolDTO, 1L);

        assertNotNull(result);
        assertEquals(poolDTO.getName(), result.getName());
        verify(poolRepository, times(1)).findById(1L);
        verify(poolRepository, times(1)).save(pool);
    }

    @Test
    void testDeletePool() {
        when(poolRepository.existsById(1L)).thenReturn(true);

        poolService.delete(1L);

        verify(poolRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindAllPools() {
        List<Pool> pools = Arrays.asList(pool);
        when(poolRepository.findAll()).thenReturn(pools);
        when(poolMapper.toDTO(pool)).thenReturn(poolDTO);

        List<PoolDTO> result = poolService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(poolDTO.getName(), result.get(0).getName());
        verify(poolRepository, times(1)).findAll();
    }

    @Test
    void testFindPoolById() {
        when(poolRepository.findById(1L)).thenReturn(Optional.of(pool));
        when(poolMapper.toDTO(pool)).thenReturn(poolDTO);

        PoolDTO result = poolService.findById(1L);

        assertNotNull(result);
        assertEquals(poolDTO.getName(), result.getName());
        verify(poolRepository, times(1)).findById(1L);
    }

    @Test
    void testFindPoolByIdNotFound() {
        when(poolRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> poolService.findById(1L));

        assertEquals("Not found pool with id: 1", exception.getMessage());
    }

    @Test
    void testDeletePoolNotFound() {
        when(poolRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> poolService.delete(1L));

        assertEquals("Pool doesn't wxist with id: 1", exception.getMessage());
    }
}