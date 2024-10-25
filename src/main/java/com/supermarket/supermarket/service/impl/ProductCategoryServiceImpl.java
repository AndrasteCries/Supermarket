package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.ProductCategory;
import com.supermarket.supermarket.repository.ProductCategoryRepository;
import com.supermarket.supermarket.service.CategoryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements CategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory create(ProductCategory productCategory) {
        checkIfManufacturerNameExists(productCategory.getName());
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory update(ProductCategory productCategory) {
        final Long id = productCategory.getId();
        if (productCategoryRepository.existsById(id)) {
            return productCategoryRepository.save(productCategory);
        }
        throw new EntityNotFoundException("ProductCategory with id: " + id + " not found");
    }

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ProductCategory with id: " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        productCategoryRepository.delete(findById(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }

    private void checkIfManufacturerNameExists(final String name) {
        if (productCategoryRepository.existsByName(name)) {
            throw new EntityExistsException("ProductCategory with name: " + name + " already exists");
        }
    }

}
