package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.Section;
import com.supermarket.supermarket.repository.SectionRepository;
import com.supermarket.supermarket.service.SectionService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;

    @Override
    public Section create(Section section) {
        checkIfSectionNameExists(section.getName());
        return sectionRepository.save(section);
    }

    @Override
    public Section update(Section section) {
        final Long id = section.getId();
        if (sectionRepository.existsById(id)) {
            return sectionRepository.save(section);
        }
        throw new EntityNotFoundException("Section with id: " + id + " not found");
    }

    @Override
    public Section findById(Long id) {
        return sectionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Section with id: " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        sectionRepository.delete(findById(id));
    }

    @Override
    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    private void checkIfSectionNameExists(final String name) {
        if (sectionRepository.existsByName(name)) {
            throw new EntityExistsException("Section with name: " + name + " already exists");
        }
    }
}
