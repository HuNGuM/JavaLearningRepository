package com.example.demo.controller;

import com.example.demo.dto.PoolDTO;
import com.example.demo.entity.Pool;
import com.example.demo.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pools")
public class PoolController {
    private final PoolService poolService;

    @Autowired
    public PoolController(PoolService poolService) {
        this.poolService = poolService;
    }

    @PostMapping
    public ResponseEntity<PoolDTO> createPool(@RequestBody PoolDTO poolDTO) {
        PoolDTO createPool = poolService.create(poolDTO);
        return new ResponseEntity<>(createPool, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoolDTO> updatePool(@RequestBody PoolDTO poolDTO, @PathVariable Long id) {
        PoolDTO updatePool = poolService.update(poolDTO, id);
        return new ResponseEntity<>(updatePool, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PoolDTO> deletePool(@PathVariable Long id) {
        poolService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoolDTO> getPoolById(@PathVariable Long id) {
        Optional<PoolDTO> poolDTOOptional = Optional.ofNullable(poolService.findById(id));
        return poolDTOOptional
                .map(pool -> new ResponseEntity<>(pool, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public ResponseEntity<List<PoolDTO>> getAllPools() {
        List<PoolDTO> pools = poolService.findAll();
        return new ResponseEntity<>(pools, HttpStatus.OK);
    }
}
