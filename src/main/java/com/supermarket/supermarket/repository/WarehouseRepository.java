package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("SELECT wp FROM Warehouse wp WHERE wp.product.id = :productId AND wp.expiryDate = :expiryDate")
    Warehouse findByProductIdAndExpiryDate(@Param("productId") Long productId, @Param("expiryDate") LocalDate expiryDate);
}
