package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.Promotion;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PromotionService {
    Promotion create(Promotion promotion);

    Promotion update(Promotion promotion);

    List<Promotion> getAll();

    Promotion findById(Long id);

    List<Promotion> getCurrentPromotion();

    void delete(Long id);

    List<Product> getAllProducts();
}
