package com.agvsistemas.dscommerce.services;

import com.agvsistemas.dscommerce.dto.ProductDTO;
import com.agvsistemas.dscommerce.entities.Product;
import com.agvsistemas.dscommerce.exceptions.DatabaseException;
import com.agvsistemas.dscommerce.exceptions.ResourceNotFoundException;
import com.agvsistemas.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findALl(String name, Pageable pageable) {
        var entityList = productRepository.searchByName(name, pageable);
        return entityList.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        var entity = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        return new ProductDTO(entity);
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    public void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setImgUrl(dto.imgUrl());
    }
}
