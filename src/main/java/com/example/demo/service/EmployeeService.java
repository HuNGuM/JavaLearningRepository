package com.example.demo.service;


import com.example.demo.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public EmployeeDTO create(EmployeeDTO employeeDTO);
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id);
    public void delete(Long id);
    public EmployeeDTO findById(Long id);
    public List<EmployeeDTO> findAll();
}
