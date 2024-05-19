package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    //SELECT EXISTS(SELECT 1 FROM sections WHERE name = '');
    boolean existsByName(String name);
    @Query("SELECT p FROM Promotion p WHERE :date BETWEEN p.startDate AND p.endDate")
    List<Promotion> findCurrentPromotions(@Param("date") LocalDate date);
}
