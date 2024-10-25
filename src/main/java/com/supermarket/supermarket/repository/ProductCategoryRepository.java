package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository <ProductCategory, Long> {
    //SELECT EXISTS(SELECT 1 FROM product_categories WHERE name = '');
    boolean existsByName(String name);
}
