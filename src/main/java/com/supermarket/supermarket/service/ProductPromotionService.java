package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ProductPromotionService {
    ProductPromotion create(ProductPromotion productPromotion);

    ProductPromotion update(ProductPromotion productPromotion);

    List<ProductPromotion> getAll();

    ProductPromotion findById(Long id);
    List<ProductPromotion> findAllByPromotion(Long id);

    List<ProductPromotion> getCurrentPromotion();

    void delete(Long id);

    List<Product> getAllProducts();

    List<Promotion> getAllPromotion();



}
