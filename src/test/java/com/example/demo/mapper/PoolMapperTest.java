//package com.example.demo.mapper;
//
//import com.example.demo.dto.PoolDTO;
//import com.example.demo.entity.Pool;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PoolMapperTest {
//    private final PoolMapper poolMapper = Mappers.getMapper(PoolMapper.class);
//
//    @Test
//    public void testToDTO() {
//        Pool pool = new Pool(1L, "Main Pool", "Downtown", 6, "08:00 - 20:00");
//
//        PoolDTO poolDTO = poolMapper.toDTO(pool);
//
//        assertEquals(pool.getId(), poolDTO.getId());
//        assertEquals(pool.getName(), poolDTO.getName());
//        assertEquals(pool.getLocation(), poolDTO.getLocation());
//        assertEquals(pool.getLanes(), poolDTO.getLanes());
//        assertEquals(pool.getSchedule(), poolDTO.getSchedule());
//    }
//
//    @Test
//    public void testToEntity() {
//        PoolDTO poolDTO = new PoolDTO(1L, "Main Pool", "Downtown", 6, "08:00 - 20:00");
//
//        Pool pool = poolMapper.toEntity(poolDTO);
//
//        assertEquals(poolDTO.getId(), pool.getId());
//        assertEquals(poolDTO.getName(), pool.getName());
//        assertEquals(poolDTO.getLocation(), pool.getLocation());
//        assertEquals(poolDTO.getLanes(), pool.getLanes());
//        assertEquals(poolDTO.getSchedule(), pool.getSchedule());
//    }
//
//    @Test
//    public void testToDTOList() {
//        Pool pool1 = new Pool(1L, "Main Pool", "Downtown", 6, "08:00 - 20:00");
//        Pool pool2 = new Pool(2L, "Training Pool", "Uptown", 4, "09:00 - 18:00");
//        List<Pool> pools = Arrays.asList(pool1, pool2);
//
//        List<PoolDTO> poolDTOs = poolMapper.toDTOList(pools);
//
//        assertEquals(2, poolDTOs.size());
//        assertEquals(pool1.getId(), poolDTOs.get(0).getId());
//        assertEquals(pool2.getId(), poolDTOs.get(1).getId());
//    }
//
//    @Test
//    public void testToEntityList() {
//        PoolDTO poolDTO1 = new PoolDTO(1L, "Main Pool", "Downtown", 6, "08:00 - 20:00");
//        PoolDTO poolDTO2 = new PoolDTO(2L, "Training Pool", "Uptown", 4, "09:00 - 18:00");
//        List<PoolDTO> poolDTOs = Arrays.asList(poolDTO1, poolDTO2);
//
//        List<Pool> pools = poolMapper.toEntityList(poolDTOs);
//
//        assertEquals(2, pools.size());
//        assertEquals(poolDTO1.getId(), pools.get(0).getId());
//        assertEquals(poolDTO2.getId(), pools.get(1).getId());
//    }
//}