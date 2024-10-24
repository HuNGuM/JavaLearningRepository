package com.example.demo.service.impl;

import com.example.demo.dto.PoolDTO;
import com.example.demo.mapper.PoolMapper;
import com.example.demo.entity.Pool;
import com.example.demo.repository.PoolRepository;
import com.example.demo.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
        Pool poolFromRepository = poolRepository.save(pool);
        return poolMapper.toDTO(poolFromRepository);
    }

    @Override
    public PoolDTO update(PoolDTO updatePoolDTO, Long id) {
        Pool pool = poolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found pool with id: " + id));
        pool.setName(updatePoolDTO.getName());
        pool.setLocation(updatePoolDTO.getLocation());
        pool.setLanes(updatePoolDTO.getLanes());
        pool.setSchedule(updatePoolDTO.getSchedule());
        Pool updatePool = poolRepository.save(pool);
        return poolMapper.toDTO(updatePool);
    }

    @Override
    public void delete(Long id) {
        if (!poolRepository.existsById(id)) {
            throw new RuntimeException("Pool doesn't wxist with id: " + id);
        }
        poolRepository.deleteById(id);
    }

    @Override
    public List<PoolDTO> findAll() {
        return poolRepository
                .findAll()
                .stream()
                .map(poolMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PoolDTO findById(Long poolId) {
        Pool pool = poolRepository
                .findById(poolId)
                .orElseThrow(() -> new RuntimeException("Not found pool with id: " + poolId));
        return poolMapper.toDTO(pool);
    }
}