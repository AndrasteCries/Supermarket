package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Purchase findByProductId(Long id);
    List<Purchase> findByDate(LocalDate date);
}
