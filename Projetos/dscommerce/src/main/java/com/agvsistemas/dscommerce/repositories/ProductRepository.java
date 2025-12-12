package com.agvsistemas.dscommerce.repositories;

import com.agvsistemas.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
