package com.agvsistemas.dscommerce.dto;

import com.agvsistemas.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO(
        Long id,
        @NotBlank(message = "The product name can't be not blank.")
        @Size(min = 3, max = 80)
        String name,
        @NotBlank(message = "The product description can't be not blank.")
        @Size(min = 10, message = "The product description needs to be more than 10 characters.")
        String description,
        @Positive(message = "The product price needs to be positive.")
        Double price,
        String imgUrl) {

    public ProductDTO(Product entity) {
        this(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImgUrl()
        );
    }
}
