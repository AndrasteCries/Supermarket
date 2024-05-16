package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
     List<Product> findAllProduct();
     Product findByName(String name);

     Product saveProduct(Product product);
     Product updateProduct(Product product);

     //todo or id (ya ne znauy kak pravilno)
     void deleteStudent(String name);

     //todo add category table
     List<Product> findAllProductByCategory(String category);

}
