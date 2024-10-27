package com.example.demo.service.impl;

import com.example.demo.dto.RoleDTO;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        Role roleFromRepository = roleRepository.save(role);
        return roleMapper.toDTO(roleFromRepository);
    }

    @Override
    public RoleDTO update(RoleDTO updateRoleDTO, Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found role with id: " + id));
        role.setName(updateRoleDTO.getName());
        Role updateRole = roleRepository.save(role);
        return roleMapper.toDTO(updateRole);
    }

    @Override
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role doesn't exist with id: " + id);
        }
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long roleId) {
        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() -> new RuntimeException("Not found role with id: " + roleId));
        return roleMapper.toDTO(role);
    }
}
