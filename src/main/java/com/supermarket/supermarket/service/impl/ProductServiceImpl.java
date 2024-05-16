package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.repository.ProductRepository;
import com.supermarket.supermarket.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
//    @Override
//    public List<Product> findAllProduct(){
//        return repository.findAll();
//    }
    //todo
//    @Override
//    public Product findByName(String name) {
//        return repository.findByName(name);
//    }
//    @Override
//    public List<Product> findAllProductByCategory(String category) {
//        return repository.findAllProductByCategory(category);
//    }

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteProduct(String name) {
        repository.deleteByName(name);
    }
}
