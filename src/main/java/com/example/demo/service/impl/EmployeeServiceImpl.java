package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeMapper employeeMapper;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder; // Добавляем это поле
    private KafkaProducerService kafkaProducerService;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder; // Внедряем PasswordEncoder
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        // Кодируем пароль перед созданием объекта Employee
        String encodedPassword = passwordEncoder.encode(employeeDTO.getPassword());
        employeeDTO.setPassword(encodedPassword); // Устанавливаем закодированный пароль

        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee employeeFromRepository = employeeRepository.save(employee);
        return employeeMapper.toDTO(employeeFromRepository);
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found emloyee with id: " + id));
        existingEmployee.setFio(employeeDTO.getFio());
        existingEmployee.setRole_id(employeeDTO.getRole_id());
        existingEmployee.setLogin(employeeDTO.getLogin());
        existingEmployee.setPassword(employeeDTO.getPassword());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        // Отправка сообщения в Kafka после добавления сотрудника
        String employeeMessage = "New employee created: " + updatedEmployee.getFio();
        kafkaProducerService.sendEmployeeCreatedMessage(employeeMessage);

        return employeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id))
            throw new RuntimeException("Employee doesn't exist with id: " + id);
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Not found employee with id: " + id));
        return employeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
