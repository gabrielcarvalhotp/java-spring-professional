package com.agvsistemas.crudclientes.controllers;

import com.agvsistemas.crudclientes.dto.ClientDTO;
import com.agvsistemas.crudclientes.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        var clients = service.findAll(pageable);
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        var client = service.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    private ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) {
        var client = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        var client = service.update(id, dto);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }    
}
