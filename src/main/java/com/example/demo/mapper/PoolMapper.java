package com.example.demo.mapper;

import com.example.demo.dto.PoolDTO;
import com.example.demo.entity.Pool;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PoolMapper {
    PoolMapper INSTANCE = Mappers.getMapper(PoolMapper.class);

    PoolDTO toDTO(Pool pool);
    Pool toEntity(PoolDTO poolDTO);
    List<PoolDTO> toDTOList(List<Pool> poolList);
    List<Pool> toEntityList(List<PoolDTO> poolDTOList);
}
