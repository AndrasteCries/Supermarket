package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    //SELECT EXISTS(SELECT 1 FROM sections WHERE name = '');
    boolean existsByName(String name);
}
