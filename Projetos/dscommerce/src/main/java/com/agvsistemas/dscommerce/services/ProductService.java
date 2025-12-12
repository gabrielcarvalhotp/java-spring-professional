package com.agvsistemas.dscommerce.services;

import com.agvsistemas.dscommerce.dto.ProductDTO;
import com.agvsistemas.dscommerce.entities.Product;
import com.agvsistemas.dscommerce.exceptions.ResourceNotFoundException;
import com.agvsistemas.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDTO> findALl(Pageable pageable) {
        var entityList = productRepository.findAll(pageable);
        return entityList.map(ProductDTO::new);
    }

    public ProductDTO findById(Long id) {
        var entity = productRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("Product #" + id.toString() + " not found!");
        }
        return new ProductDTO(entity.get());
    }

    public ProductDTO create(ProductDTO dto) {
        var entity = new Product();
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setImgUrl(dto.imgUrl());

        entity = productRepository.save(entity);

        return new ProductDTO(entity);
    }
}
