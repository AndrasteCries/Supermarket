package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
//     List<Product> findAllProduct();

     //todo
//     Product findByName(String name);

     Product saveProduct(Product product);
     Product updateProduct(Product product);

     //todo or id (ya ne znauy kak pravilno)
     void deleteProduct(String name);

     //todo add category table
//     List<Product> findAllProductByCategory(String category);

}
