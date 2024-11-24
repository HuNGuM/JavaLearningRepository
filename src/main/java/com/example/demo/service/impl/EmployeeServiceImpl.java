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
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeMapper employeeMapper;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private KafkaProducerService kafkaProducerService;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, KafkaProducerService kafkaProducerService) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        // Кодируем пароль перед созданием объекта Employee
        String encodedPassword = passwordEncoder.encode(employeeDTO.getPassword());
        employeeDTO.setPassword(encodedPassword);

        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        // Отправка сообщения в Kafka после создания сотрудника
        String employeeMessage = "New employee created: " + savedEmployee.getFio();
//        kafkaProducerService.sendEmployeeCreatedMessage(employeeMessage);

        return employeeMapper.toDTO(savedEmployee);
    }
@Override
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id) {
        // Получаем существующего сотрудника
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found employee with id: " + id));

        // Обновляем данные сотрудника
        existingEmployee.setFio(employeeDTO.getFio());
        existingEmployee.setRole(employeeDTO.getRole());  // Здесь предполагаем, что роль передается как сущность
        existingEmployee.setLogin(employeeDTO.getLogin());
        existingEmployee.setPassword(employeeDTO.getPassword());

        // Сохраняем обновленного сотрудника
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        // Отправляем сообщение в Kafka, если обновление прошло успешно
// Отправка сообщения в Kafka после добавления сотрудника
    String employeeMessage = "New employee created: " + updatedEmployee.getFio();
//    kafkaProducerService.sendEmployeeCreatedMessage(employeeMessage);

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
