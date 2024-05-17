package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    ProductCategory create(ProductCategory productCategory);
    ProductCategory update(ProductCategory productCategory);
    List<ProductCategory> getAll();
    ProductCategory findById(Long id);
    void delete(Long id);

}