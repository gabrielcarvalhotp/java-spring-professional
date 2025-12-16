package com.agvsistemas.crudclientes.services;

import com.agvsistemas.crudclientes.dto.ClientDTO;
import com.agvsistemas.crudclientes.entities.Client;
import com.agvsistemas.crudclientes.exceptions.DatabaseException;
import com.agvsistemas.crudclientes.exceptions.ResourceNotFoundException;
import com.agvsistemas.crudclientes.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        var clientList = repository.findAll(pageable);
        return clientList.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        var client = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO create(ClientDTO dto) {
        var entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("The product you want to update was not found.");
        }
        var entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    public void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.name());
        entity.setCpf(dto.cpf());
        entity.setIncome(dto.income());
        entity.setBirthDate(dto.birthDate());
        entity.setChildren(dto.children());
    }
}
