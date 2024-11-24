package com.example.demo.service.impl;

import com.example.demo.dto.PoolDTO;
import com.example.demo.entity.Pool;
import com.example.demo.mapper.PoolMapper;
import com.example.demo.repository.PoolRepository;
import com.example.demo.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoolServiceImpl implements PoolService {

    private final PoolRepository poolRepository;
    private final PoolMapper poolMapper;

    @Autowired
    public PoolServiceImpl(PoolRepository poolRepository, PoolMapper poolMapper) {
        this.poolRepository = poolRepository;
        this.poolMapper = poolMapper;
    }

    @Override
    public PoolDTO create(PoolDTO poolDTO) {
        Pool pool = poolMapper.toEntity(poolDTO);
        Pool savedPool = poolRepository.save(pool);
        return poolMapper.toDTO(savedPool);
    }

    @Override
    public PoolDTO update(PoolDTO poolDTO, Long id) {
        Pool existingPool = poolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pool not found with id: " + id));

        // Обновляем данные существующего бассейна
        existingPool.setName(poolDTO.getName());
        existingPool.setLocation(poolDTO.getLocation());
        existingPool.setLanes(poolDTO.getLanes());
        existingPool.setSchedule(poolDTO.getSchedule());
        existingPool.setEmployee(poolDTO.getEmployee());

        Pool updatedPool = poolRepository.save(existingPool);
        return poolMapper.toDTO(updatedPool);
    }

    @Override
    public void delete(Long id) {
        Pool pool = poolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pool not found with id: " + id));
        poolRepository.delete(pool);
    }

    @Override
    public List<PoolDTO> findAll() {
        List<Pool> pools = poolRepository.findAll();
        return poolMapper.toDTOList(pools);
    }

    @Override
    public PoolDTO findById(Long poolDTOId) {
        Pool pool = poolRepository.findById(poolDTOId)
                .orElseThrow(() -> new RuntimeException("Pool not found with id: " + poolDTOId));
        return poolMapper.toDTO(pool);
    }
}