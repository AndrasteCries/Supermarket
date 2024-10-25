package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository <Manufacturer, Long> {
    //SELECT EXISTS(SELECT 1 FROM manufacturers WHERE name = '');
    boolean existsByName(String name);
}
