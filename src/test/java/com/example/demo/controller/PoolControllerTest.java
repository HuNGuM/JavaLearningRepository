package com.example.demo.controller;

import com.example.demo.dto.PoolDTO;
import com.example.demo.entity.Pool;
import com.example.demo.repository.PoolRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PoolRepository poolRepository;

    @BeforeEach
    void setUp() {
        poolRepository.deleteAll();
    }

    @Test
    void testCreatePool() throws Exception {
        PoolDTO poolDTO = new PoolDTO();
        poolDTO.setName("Test Pool");
        poolDTO.setLocation("Test Location");
        poolDTO.setLanes(5);
        poolDTO.setSchedule("09:00-18:00");

        mockMvc.perform(post("/pools")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(poolDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Pool"));
    }

    @Test
    void testGetPoolById() throws Exception {
        Pool pool = new Pool();
        pool.setName("Test Pool");
        pool.setLocation("Test Location");
        pool.setLanes(5);
        pool.setSchedule("09:00-18:00");
        poolRepository.save(pool);

        mockMvc.perform(get("/pools/{id}", pool.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Pool"));
    }

    @Test
    void testGetAllPools() throws Exception {
        Pool pool1 = new Pool();
        pool1.setName("Test Pool 1");
        pool1.setLocation("Location 1");
        pool1.setLanes(3);
        pool1.setSchedule("09:00-18:00");
        poolRepository.save(pool1);

        Pool pool2 = new Pool();
        pool2.setName("Test Pool 2");
        pool2.setLocation("Location 2");
        pool2.setLanes(5);
        pool2.setSchedule("10:00-20:00");
        poolRepository.save(pool2);

        mockMvc.perform(get("/pools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)));
    }

    @Test
    void testUpdatePool() throws Exception {
        Pool pool = new Pool();
        pool.setName("Test Pool");
        pool.setLocation("Test Location");
        pool.setLanes(5);
        pool.setSchedule("09:00-18:00");
        poolRepository.save(pool);

        PoolDTO updatePoolDTO = new PoolDTO();
        updatePoolDTO.setName("Updated Pool");
        updatePoolDTO.setLocation("Updated Location");
        updatePoolDTO.setLanes(6);
        updatePoolDTO.setSchedule("10:00-19:00");

        mockMvc.perform(put("/pools/{id}", pool.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatePoolDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Pool"));
    }

    @Test
    void testDeletePool() throws Exception {
        Pool pool = new Pool();
        pool.setName("Test Pool");
        pool.setLocation("Test Location");
        pool.setLanes(5);
        pool.setSchedule("09:00-18:00");
        poolRepository.save(pool);

        mockMvc.perform(delete("/pools/{id}", pool.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetPoolNotFound() throws Exception {
        mockMvc.perform(get("/pools/{id}", 998L))
                .andExpect(status().isNotFound());
    }
}