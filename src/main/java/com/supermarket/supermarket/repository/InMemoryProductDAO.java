package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Product;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductDAO {
    public final List<Product> PRODUCTS = new ArrayList<>();

    public List<Product> findAllProduct(){
        return PRODUCTS;
    }

    public Product findByName(String name) {
        return PRODUCTS.stream().filter(element -> element.getName()
                .equals(name))
                .findFirst()
                .orElse(null);
    }
    //todo
    public List<Product> findAllProductByCategory(String category) {
        return null;
    }

    public Product saveProduct(Product product) {
        PRODUCTS.add(product);
        return product;
    }

    public Product updateProduct(Product product) {
        var productIndex = IntStream.range(0, PRODUCTS.size())
                .filter(index -> PRODUCTS.get(index).getName().equals(product.getName()))
                .findFirst()
                .orElse(-1);
        if (productIndex > -1){
            PRODUCTS.set(productIndex, product);
            return product;
        }
        return null;
    }

    public void deleteStudent(String name) {
        var product = findByName(name);
        if (product != null){
            PRODUCTS.remove(product);
        }
    }

}
