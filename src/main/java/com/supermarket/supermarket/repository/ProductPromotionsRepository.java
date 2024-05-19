package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.ProductPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.time.LocalDate;

public interface ProductPromotionsRepository extends JpaRepository<ProductPromotion, Long> {
    @Query("SELECT p FROM ProductPromotion p WHERE p.promotion.id = :p_id")
    List<ProductPromotion> findAllByPromotion(@Param("p_id") Long id);
    @Query("SELECT p FROM Promotion p WHERE :date BETWEEN p.startDate AND p.endDate")
    List<ProductPromotion> getCurrentPromotion(LocalDate date);

    @Query("SELECT CASE WHEN COUNT(pp) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ProductPromotion pp " +
            "WHERE pp.product.id = :productId AND pp.promotion.id = :promotionId")
    boolean existsByProductAndPromotion(@Param("productId") Long productId, @Param("promotionId") Long promotionId);
}
