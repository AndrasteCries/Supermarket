package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.Manufacturer;
import com.supermarket.supermarket.repository.ManufacturerRepository;
import com.supermarket.supermarket.service.ManufacturerService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        checkIfManufacturerNameExists(manufacturer.getName());
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        final Long id = manufacturer.getId();
        if (manufacturerRepository.existsById(id)) {
            return manufacturerRepository.save(manufacturer);
        }
        throw new EntityNotFoundException("Manufacturer with id: " + id + " not found");
    }

    @Override
    public Manufacturer findById(Long id) {
        return manufacturerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Manufacturer with id: " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        manufacturerRepository.delete(findById(id));
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerRepository.findAll();
    }

    private void checkIfManufacturerNameExists(final String name) {
        if (manufacturerRepository.existsByName(name)) {
            throw new EntityExistsException("Manufacturer with name: " + name + " already exists");
        }
    }

}
