package com.example.demo.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PoolDTOTest {

    @Test
    void testDefaultConstructor() {
        PoolDTO poolDTO = new PoolDTO();
        assertNull(poolDTO.getId());
        assertNull(poolDTO.getName());
        assertNull(poolDTO.getLocation());
        assertEquals(0, poolDTO.getLanes());
        assertNull(poolDTO.getSchedule());
    }

    @Test
    void testParameterizedConstructor() {
        PoolDTO poolDTO = new PoolDTO(1L, "Olympic Pool", "Downtown", 8, "Mon-Fri: 6AM-10PM");
        assertEquals(1L, poolDTO.getId());
        assertEquals("Olympic Pool", poolDTO.getName());
        assertEquals("Downtown", poolDTO.getLocation());
        assertEquals(8, poolDTO.getLanes());
        assertEquals("Mon-Fri: 6AM-10PM", poolDTO.getSchedule());
    }

    @Test
    void testGetAndSetId() {
        PoolDTO poolDTO = new PoolDTO();
        poolDTO.setId(1L);
        assertEquals(1L, poolDTO.getId());
    }

    @Test
    void testGetAndSetName() {
        PoolDTO poolDTO = new PoolDTO();
        poolDTO.setName("Olympic Pool");
        assertEquals("Olympic Pool", poolDTO.getName());
    }

    @Test
    void testGetAndSetLocation() {
        PoolDTO poolDTO = new PoolDTO();
        poolDTO.setLocation("Downtown");
        assertEquals("Downtown", poolDTO.getLocation());
    }

    @Test
    void testGetAndSetLanes() {
        PoolDTO poolDTO = new PoolDTO();
        poolDTO.setLanes(8);
        assertEquals(8, poolDTO.getLanes());
    }

    @Test
    void testGetAndSetSchedule() {
        PoolDTO poolDTO = new PoolDTO();
        poolDTO.setSchedule("Mon-Fri: 6AM-10PM");
        assertEquals("Mon-Fri: 6AM-10PM", poolDTO.getSchedule());
    }
}