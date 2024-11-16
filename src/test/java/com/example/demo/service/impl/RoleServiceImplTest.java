package com.example.demo.service.impl;

import com.example.demo.dto.RoleDTO;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoleServiceImplTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setName("Admin");
        roleRepository.save(role);
    }

    @Test
    void createRole_ShouldReturnRoleDTO() {
        // Arrange
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("User");

        // Act
        RoleDTO result = roleService.create(roleDTO);

        // Assert
        assertNotNull(result);
        assertEquals("User", result.getName());
        assertNotNull(result.getId());
    }

    @Test
    void updateRole_ShouldReturnUpdatedRoleDTO() {
        // Arrange
        RoleDTO updateRoleDTO = new RoleDTO();
        updateRoleDTO.setName("Updated Name");

        // Act
        RoleDTO result = roleService.update(updateRoleDTO, role.getId());

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
    }

    @Test
    void deleteRole_ShouldDeleteRole() {
        // Act
        roleService.delete(role.getId());

        // Assert
        assertFalse(roleRepository.existsById(role.getId()));
    }

    @Test
    void findAllRoles_ShouldReturnListOfRoleDTO() {
        // Arrange
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("User");
        roleService.create(roleDTO);

        // Act
        List<RoleDTO> result = roleService.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void findRoleById_ShouldReturnRoleDTO() {
        // Act
        RoleDTO result = roleService.findById(role.getId());

        // Assert
        assertNotNull(result);
        assertEquals("Admin", result.getName());
    }
}