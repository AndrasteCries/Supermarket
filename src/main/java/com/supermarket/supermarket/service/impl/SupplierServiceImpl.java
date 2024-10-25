package com.supermarket.supermarket.service.impl;


import com.supermarket.supermarket.model.Supplier;
import com.supermarket.supermarket.repository.SupplierRepository;

import com.supermarket.supermarket.service.SupplierService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier create(Supplier supplier) {
        checkIfSupplierNameExists(supplier.getName());
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier update(Supplier supplier) {
        final Long id = supplier.getId();
        if (supplierRepository.existsById(id)) {
            return supplierRepository.save(supplier);
        }
        throw new EntityNotFoundException("Supplier with id: " + id + " not found");
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Supplier with id: " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        supplierRepository.delete(findById(id));
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    private void checkIfSupplierNameExists(final String name) {
        if (supplierRepository.existsByName(name)) {
            throw new EntityExistsException("Supplier with name: " + name + " already exists");
        }
    }
}
