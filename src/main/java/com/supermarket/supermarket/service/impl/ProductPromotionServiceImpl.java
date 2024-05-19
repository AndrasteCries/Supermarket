package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.ProductPromotion;
import com.supermarket.supermarket.model.Promotion;
import com.supermarket.supermarket.repository.ProductPromotionsRepository;
import com.supermarket.supermarket.repository.ProductRepository;
import com.supermarket.supermarket.repository.PromotionRepository;
import com.supermarket.supermarket.service.ProductPromotionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductPromotionServiceImpl implements ProductPromotionService {

    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final ProductPromotionsRepository productPromotionsRepository;

    public ProductPromotionServiceImpl(ProductRepository productRepository
            , PromotionRepository promotionRepository, ProductPromotionsRepository productPromotionsRepository) {
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
        this.productPromotionsRepository = productPromotionsRepository;
    }

    @Override
    public ProductPromotion create(ProductPromotion productPromotion) {
        Product product = productRepository.findById(productPromotion.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Promotion promotion = promotionRepository.findById(productPromotion.getPromotion().getId())
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        productPromotion.setProduct(product);
        productPromotion.setPromotion(promotion);

        return productPromotionsRepository.save(productPromotion);
    }


    @Override
    public ProductPromotion update(ProductPromotion productPromotion) {
        final Long id = productPromotion.getId();
        if (productPromotionsRepository.existsById(id)) {
            return productPromotionsRepository.save(productPromotion);
        }
        throw new EntityNotFoundException("ProductPromotion with id: " + id + " not found");
    }

    @Override
    public List<ProductPromotion> getAll() {
        return productPromotionsRepository.findAll();
    }

    @Override
    public ProductPromotion findById(Long id) {
        return productPromotionsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ProductPromotion with id: " + id + " not found"));
    }

    @Override
    public List<ProductPromotion> findAllByPromotion(Long id) {
        return productPromotionsRepository.findAllByPromotion(id);
    }

    @Override
    public List<ProductPromotion> getCurrentPromotion() {
        LocalDate currentDate = LocalDate.now();
        return productPromotionsRepository.getCurrentPromotion(currentDate);
    }

    @Override
    public void delete(Long id) {
        productPromotionsRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAll();
    }

}
