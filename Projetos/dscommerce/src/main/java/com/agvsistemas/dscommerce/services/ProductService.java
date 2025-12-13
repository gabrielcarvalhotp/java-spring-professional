package com.agvsistemas.dscommerce.services;

import com.agvsistemas.dscommerce.dto.ProductDTO;
import com.agvsistemas.dscommerce.entities.Product;
import com.agvsistemas.dscommerce.exceptions.ResourceNotFoundException;
import com.agvsistemas.dscommerce.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Page<ProductDTO> findALl(Pageable pageable) {
        var entityList = productRepository.findAll(pageable);
        return entityList.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO findById(Long id) {
        var entity = productRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("Product #" + id + " not found!");
        }
        return new ProductDTO(entity.get());
    }

    @Transactional
    public ProductDTO create(ProductDTO dto) {
        var entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        var entity = productRepository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setImgUrl(dto.imgUrl());
    }
}
