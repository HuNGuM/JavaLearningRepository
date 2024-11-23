package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @BeforeEach
    void setup() {
        // Очистить базу данных перед каждым тестом
        employeeRepository.deleteAll();

        // Добавить тестовые данные
        employeeRepository.save(new Employee(null, "John Doe", 1, "jdoe", passwordEncoder.encode("password123")));
        employeeRepository.save(new Employee(null, "Jane Smith", 2, "jsmith", passwordEncoder.encode("password456")));
    }

    @Test
    void createEmployee_shouldAddNewEmployee() {
        EmployeeDTO newEmployee = new EmployeeDTO(1, "Alice Johnson", 3, "ajohnson", "password789");

        EmployeeDTO createdEmployee = employeeService.create(newEmployee);

        assertThat(createdEmployee.getId()).isNotNull();
        assertThat(createdEmployee.getFio()).isEqualTo("Alice Johnson");
        assertThat(createdEmployee.getLogin()).isEqualTo("ajohnson");
        assertThat(passwordEncoder.matches("password789", createdEmployee.getPassword())).isTrue();

        List<Employee> allEmployees = employeeRepository.findAll();
        assertThat(allEmployees).hasSize(3);
    }

    @Test
    void updateEmployee_shouldModifyExistingEmployee() {
        Long existingEmployeeId = employeeRepository.findAll().get(0).getId();
        EmployeeDTO updatedEmployee = new EmployeeDTO(existingEmployeeId, "John Doe Updated", 2, "jdoe_updated", "newpassword123");
        EmployeeDTO result = employeeService.update(updatedEmployee, existingEmployeeId);

        assertThat(result.getFio()).isEqualTo("John Doe Updated");
        assertThat(result.getLogin()).isEqualTo("jdoe_updated");

        Employee updatedEntity = employeeRepository.findById(existingEmployeeId).orElseThrow();
        assertThat(updatedEntity.getFio()).isEqualTo("John Doe Updated");
        assertThat(updatedEntity.getLogin()).isEqualTo("jdoe_updated");
    }

    @Test
    void deleteEmployee_shouldRemoveEmployee() {
        Long employeeId = employeeRepository.findAll().get(0).getId();

        employeeService.delete(employeeId);

        assertThat(employeeRepository.existsById(employeeId)).isFalse();
        assertThat(employeeRepository.findAll()).hasSize(1);
    }

    @Test
    void findById_shouldReturnCorrectEmployee() {
        Long employeeId = employeeRepository.findAll().get(0).getId();

        EmployeeDTO foundEmployee = employeeService.findById(employeeId);

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getId()).isEqualTo(employeeId);
        assertThat(foundEmployee.getFio()).isEqualTo("John Doe");
    }

    @Test
    void findById_shouldThrowExceptionIfNotFound() {
        Long nonExistentId = 999L;

        Exception exception = assertThrows(RuntimeException.class, () -> employeeService.findById(nonExistentId));
        assertThat(exception.getMessage()).isEqualTo("Not found employee with id: " + nonExistentId);
    }

    @Test
    void findAll_shouldReturnAllEmployees() {
        List<EmployeeDTO> employees = employeeService.findAll();

        assertThat(employees).hasSize(2);
        assertThat(employees.get(0).getFio()).isEqualTo("John Doe");
        assertThat(employees.get(1).getFio()).isEqualTo("Jane Smith");
    }
}