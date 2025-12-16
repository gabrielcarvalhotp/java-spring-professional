package com.agvsistemas.crudclientes.repositories;

import com.agvsistemas.crudclientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
