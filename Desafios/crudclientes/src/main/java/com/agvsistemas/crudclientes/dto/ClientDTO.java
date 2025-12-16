package com.agvsistemas.crudclientes.dto;

import com.agvsistemas.crudclientes.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClientDTO(
    Long id,
    @NotBlank(message = "The client name can't be not blank.")
    @Size(max = 255, message = "The client name cannot exceed 255 characters.")
    String name,
    @NotBlank(message = "The client cpf can't be not blank.")
    String cpf,
    Double income,
    @PastOrPresent(message = "The client date of birth cannot be in the future.")
    LocalDate birthDate,
    Integer children
) {
    public ClientDTO(Client entity) {
        this(
            entity.getId(),
            entity.getName(),
            entity.getCpf(),
            entity.getIncome(),
            entity.getBirthDate(),
            entity.getChildren()
        );
    }
}
