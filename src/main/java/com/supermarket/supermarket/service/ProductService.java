package com.supermarket.supermarket.service;

import com.supermarket.supermarket.dto.ProductRequest;
import com.supermarket.supermarket.model.Manufacturer;
import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.ProductCategory;
import com.supermarket.supermarket.model.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product create(ProductRequest productRequest);

    List<Product> getAll();

    Product getById(Long id);
    Product update(Long productId, ProductRequest productRequest);

    void delete(Long id);

    public Product findByName(String name);

     List<ProductCategory> getAllCategories();

     List<Manufacturer> getAllManufacturers();

     List<Section> getAllSections();

     //todo add category table
//     List<Product> findAllProductByCategory(String category);

}
