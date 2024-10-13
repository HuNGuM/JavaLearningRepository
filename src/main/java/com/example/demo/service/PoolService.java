package com.example.demo.service;

import com.example.demo.dto.PoolDTO;

import java.util.List;

public interface PoolService {
    public PoolDTO create(PoolDTO poolDTO);
    public PoolDTO update(PoolDTO poolDTO, Long id);
    public void delete(Long id);
    public List<PoolDTO> findAll();
    public PoolDTO findById(Long poolDTOId);

}
