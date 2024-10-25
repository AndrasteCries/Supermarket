package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.ProductInput;
import com.supermarket.supermarket.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductInputRepository extends JpaRepository<ProductInput, Long> {
    ProductInput findByProductId(Long id);
    ProductInput findByDate(LocalDate date);
    List<ProductInput> findBySupplier(Supplier supplier);
}
