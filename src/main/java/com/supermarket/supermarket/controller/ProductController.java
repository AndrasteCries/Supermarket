package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

//    //todo
//    @GetMapping
//    public List<Product> findAllProduct() {
//        return productService.findAllProduct();
//    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable String id){
        return null;
    }


    @PostMapping("save_product")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // /api/v1/products/pomidor
//    @GetMapping("/{name}")
//    public Product findByName(@PathVariable String name) {
//        return productService.findByName(name);
//    }

    @PutMapping("update_product")
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("delete_product/{name}")
    public void deleteProduct(@PathVariable String name) {
        productService.deleteProduct(name);
    }

}
