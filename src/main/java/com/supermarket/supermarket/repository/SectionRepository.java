package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
    //SELECT EXISTS(SELECT 1 FROM sections WHERE name = '');
    boolean existsByName(String name);
}
