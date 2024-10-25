package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    void deleteByName(String name);
}
