package com.supermarket.supermarket.service;

import com.supermarket.supermarket.dto.ProductInputRequest;
import com.supermarket.supermarket.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ProductInputSevice {
    ProductInput create(ProductInput productInput);

    ProductInput update(ProductInput productInput);

    List<ProductInput> getAll();

    ProductInput findById(Long id);

    ProductInput findByProductId(Long id);

    ProductInput findByDate(LocalDate date);

    List<ProductInput> findBySupplier(Supplier supplier);

    void delete(Long id);

    List<Product> getAllProducts();

    List<Supplier> getAllSuppliers();

}
