package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);
    Manufacturer update(Manufacturer manufacturer);
    List<Manufacturer> getAll();
    Manufacturer findById(Long id);
    void delete(Long id);

}
