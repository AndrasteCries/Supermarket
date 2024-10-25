package com.supermarket.supermarket.service;


import com.supermarket.supermarket.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {
    Supplier create(Supplier supplier);
    Supplier update(Supplier supplier);
    List<Supplier> getAll();
    Supplier findById(Long id);
    void delete(Long id);

}
