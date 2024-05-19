package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.Purchase;
import com.supermarket.supermarket.repository.ProductRepository;
import com.supermarket.supermarket.repository.PurchaseRepository;
import com.supermarket.supermarket.service.PurchaseService;
import com.supermarket.supermarket.service.WarehouseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final WarehouseService warehouseService;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductRepository productRepository, WarehouseService warehouseService) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.warehouseService = warehouseService;
    }

    @Override
    public Purchase create(Purchase purchase) {
        Long productId = purchase.getProduct().getId();
        productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (purchase.getCount().longValue() > warehouseService.getCountProductById(purchase.getProduct().getId())) {
            throw new EntityNotFoundException("Count < purchase");
        }
        warehouseService.purchaseByProductId(purchase.getProduct().getId(), purchase.getCount().longValue());
        System.out.println("I HANE REIHGRIHMIETDDH");
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase update(Purchase purchase) {
        return null;
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public Purchase findByProductId(Long id) {
        return  purchaseRepository.findByProductId(id);
    }

    @Override
    public List<Purchase> findByDate(LocalDate date) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
