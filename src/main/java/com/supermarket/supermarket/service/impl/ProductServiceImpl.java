package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.repository.InMemoryProductDAO;
import com.supermarket.supermarket.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final InMemoryProductDAO repository;
    @Override
    public List<Product> findAllProduct(){
        return repository.findAllProduct();
    }
    @Override
    public Product findByName(String name) {
        return repository.findByName(name);
    }
    @Override
    public List<Product> findAllProductByCategory(String category) {
        return repository.findAllProductByCategory(category);
    }

    @Override
    public Product saveProduct(Product product) {
        return repository.saveProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return repository.updateProduct(product);
    }

    @Override
    public void deleteStudent(String name) {
        repository.deleteStudent(name);
    }
}

//        return List.of(
//        Product.builder().name("Ogirok").price(60).expire_date("2024-02-03").build(),
//                Product.builder().name("123").price(50).expire_date("2024-02-03").build(),
//                Product.builder().name("Kirles").price(60).expire_date("2024-02-03").build()
//        );