package com.example.demo.service;

import com.example.demo.dto.PoolDTO;
import com.example.demo.mapper.PoolMapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface PoolService {
    PoolService INSTANCE = Mappers.getMapper(PoolService.class);

    public PoolDTO create(PoolDTO poolDTO);
    public PoolDTO update(PoolDTO poolDTO, Long id);
    public void delete(Long id);
    public List<PoolDTO> findAll();
    public PoolDTO findById(Long poolDTOId);

}
