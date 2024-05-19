package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.ProductPromotion;
import com.supermarket.supermarket.model.Promotion;
import com.supermarket.supermarket.model.Supplier;
import com.supermarket.supermarket.repository.ProductRepository;
import com.supermarket.supermarket.repository.PromotionRepository;
import com.supermarket.supermarket.repository.SupplierRepository;
import com.supermarket.supermarket.service.PromotionService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    @Override
    public Promotion create(Promotion promotion) {
        checkIfPromotionNameExists(promotion.getName());
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion update(Promotion promotion) {
        final Long id = promotion.getId();
        if (promotionRepository.existsById(id)) {
            return promotionRepository.save(promotion);
        }
        throw new EntityNotFoundException("Promotion with id: " + id + " not found");
    }

    @Override
    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Promotion with id: " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        promotionRepository.delete(findById(id));
    }

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    @Override
    public List<Promotion> getCurrentPromotion() {
        LocalDate currentDate = LocalDate.now();
        return promotionRepository.findCurrentPromotions(currentDate);
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    private void checkIfPromotionNameExists(final String name) {
        if (promotionRepository.existsByName(name)) {
            throw new EntityExistsException("Promotion with name: " + name + " already exists");
        }
    }
}
