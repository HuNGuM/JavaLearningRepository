//package com.example.demo.service;
//
//import com.example.demo.dto.PoolDTO;
//import com.example.demo.entity.Pool;
//import com.example.demo.mapper.PoolMapper;
//import com.example.demo.repository.PoolRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//@SpringBootTest
//public class PoolServiceTest {
//    @Autowired
//
//    private PoolService poolService;
//    @Autowired
//    private PoolRepository poolRepository;
//
//    private PoolMapper poolMapper = new PoolMapper() {
//        @Override
//        public PoolDTO toDTO(Pool pool) {
//            return null;
//        }
//
//        @Override
//        public Pool toEntity(PoolDTO poolDTO) {
//            return null;
//        }
//
//        @Override
//        public List<PoolDTO> toDTOList(List<Pool> poolList) {
//            return null;
//        }
//
//        @Override
//        public List<Pool> toEntityList(List<PoolDTO> poolDTOList) {
//            return null;
//        }
//    };
//
//    @BeforeEach
//    void setup() {
//        // Очистить базу перед каждым тестом
//        poolRepository.deleteAll();
//
//        // Добавить несколько записей для тестов
//        poolRepository.save(new Pool(null, "Pool A", "Location A", 8, "10:00-18:00"));
//        poolRepository.save(new Pool(null, "Pool B", "Location B", 10, "9:00-22:00"));
//    }
//
//    @Test
//    void createPool_shouldAddNewPool() {
//        PoolDTO newPool = new PoolDTO(null, "Pool C", "Location C", 8, "10:00-18:00");
//
//        PoolDTO createdPool = poolService.create(newPool);
//
//        assertThat(createdPool.getId()).isNotNull();
//        assertThat(createdPool.getName()).isEqualTo("Pool C");
//        assertThat(createdPool.getLocation()).isEqualTo("Location C");
//        assertThat(createdPool.getLanes()).isEqualTo(8);
//
//        List<Pool> allPools = poolRepository.findAll();
//        assertThat(allPools).hasSize(3);
//    }
//}