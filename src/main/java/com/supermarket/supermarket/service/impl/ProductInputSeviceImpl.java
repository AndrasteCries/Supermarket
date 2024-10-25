package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.*;
import com.supermarket.supermarket.repository.*;
import com.supermarket.supermarket.service.ProductInputSevice;
import com.supermarket.supermarket.service.WarehouseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductInputSeviceImpl implements ProductInputSevice {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final ProductInputRepository productInputRepository;
    private final WarehouseService warehouseService;

    public ProductInputSeviceImpl(ProductInputRepository productInputRepository,
                                  SupplierRepository supplierRepository,
                                  ProductRepository productRepository,
                                  WarehouseService warehouseService) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.productInputRepository = productInputRepository;
        this.warehouseService = warehouseService;
    }

    @Override
    public ProductInput create(ProductInput productInput) {
        productRepository.findById(productInput.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        supplierRepository.findById(productInput.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        warehouseService.addProductToWarehouse(
                productInput.getProduct(),
                productInput.getDate(),
                productInput.getCount()
        );

        return productInputRepository.save(productInput);
    }

    @Override
    public ProductInput update(ProductInput productInput) {
        final Long id = productInput.getId();
        if (productInputRepository.existsById(id)) {
            return productInputRepository.save(productInput);
        }
        throw new EntityNotFoundException("ProductInput with id: " + id + " not found");
    }

    @Override
    public List<ProductInput> getAll() {
        return productInputRepository.findAll();
    }

    @Override
    public ProductInput findById(Long id) {
        return productInputRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ProductInput with id: " + id + " not found"));
    }

    @Override
    public ProductInput findByProductId(Long id) {
        return productInputRepository.findByProductId(id);
    }

    @Override
    public ProductInput findByDate(LocalDate date) {
        return productInputRepository.findByDate(date);
    }

    @Override
    public List<ProductInput> findBySupplier(Supplier supplier) {
        return productInputRepository.findBySupplier(supplier);
    }

    @Override
    public void delete(Long id) {
        productInputRepository.delete(findById(id));
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }
}
