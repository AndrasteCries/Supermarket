package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.Purchase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface PurchaseService {
    Purchase create(Purchase purchase);

    Purchase update(Purchase purchase);

    List<Purchase> getAll();

    Optional<Purchase> findById(Long id);

    Purchase findByProductId(Long id);

    List<Purchase> findByDate(LocalDate date);

    void delete(Long id);

    List<Product> getAllProducts();

}
