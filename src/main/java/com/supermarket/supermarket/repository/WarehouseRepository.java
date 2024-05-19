package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query("SELECT SUM(w.count) FROM Warehouse w WHERE w.product.id = :productId")
    Long getCountProductById(@Param("productId") Long productId);

    @Query("SELECT wp FROM Warehouse wp WHERE wp.product.id = :productId AND wp.expiryDate = :expiryDate")
    Warehouse findByProductIdAndExpiryDate(@Param("productId") Long productId, @Param("expiryDate") LocalDate expiryDate);

    @Query("SELECT wp FROM Warehouse wp WHERE wp.count > 0 AND wp.product.id = :productId ORDER BY wp.expiryDate ASC")
    List<Warehouse> getAllByProductId(@Param("productId") Long id);
}
