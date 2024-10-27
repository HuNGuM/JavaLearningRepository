package com.example.demo.service;

import com.example.demo.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    public RoleDTO create(RoleDTO roleDTO);
    public RoleDTO update(RoleDTO roleDTO, Long id);
    public void delete(Long id);
    public List<RoleDTO> findAll();
    public RoleDTO findById(Long roleDTOId);
}
