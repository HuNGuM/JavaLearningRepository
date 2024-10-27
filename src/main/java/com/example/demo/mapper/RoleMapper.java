package com.example.demo.mapper;

import com.example.demo.dto.PoolDTO;
import com.example.demo.dto.RoleDTO;
import com.example.demo.entity.Pool;
import com.example.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")

public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
    List<RoleDTO> toDTOList(List<Role> roleList);
    List<Role> toEntityList(List<RoleDTO> roleDTOList);
}
