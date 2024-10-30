package com.example.demo.controller;

import com.example.demo.dto.PoolDTO;
import com.example.demo.service.PoolService;
import com.example.demo.service.impl.PoolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PoolController.class)
public class PoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Change this from @Mock to @MockBean
    private PoolService poolService;

    private PoolDTO poolDTO;

    @BeforeEach
    public void setUp() {
        poolDTO = new PoolDTO();
        poolDTO.setId(1L);
        poolDTO.setName("Test Pool");
    }

    @Test
    public void testCreatePool() throws Exception {
        when(poolService.create(any(PoolDTO.class))).thenReturn(poolDTO);

        mockMvc.perform(post("/pools")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Pool\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Pool"));

        verify(poolService, times(1)).create(any(PoolDTO.class));
    }

    @Test
    public void testUpdatePool() throws Exception {
        when(poolService.update(any(PoolDTO.class), eq(1L))).thenReturn(poolDTO);

        mockMvc.perform(put("/pools/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Pool\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Pool"));

        verify(poolService, times(1)).update(any(PoolDTO.class), eq(1L));
    }

    @Test
    public void testDeletePool() throws Exception {
        doNothing().when(poolService).delete(1L); // Ensure that delete is mocked properly

        mockMvc.perform(delete("/pools/1"))
                .andExpect(status().isNoContent());

        verify(poolService, times(1)).delete(1L);
    }

    @Test
    public void testGetPoolById() throws Exception {
        when(poolService.findById(1L)).thenReturn(poolDTO);

        mockMvc.perform(get("/pools/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Pool"));

        verify(poolService, times(1)).findById(1L);
    }

    @Test
    public void testGetAllPools() throws Exception {
        List<PoolDTO> pools = Collections.singletonList(poolDTO);
        when(poolService.findAll()).thenReturn(pools);

        mockMvc.perform(get("/pools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Test Pool"));

        verify(poolService, times(1)).findAll();
    }
}