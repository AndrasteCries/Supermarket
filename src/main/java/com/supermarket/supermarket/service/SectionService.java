package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SectionService {
    Section create(Section section);
    Section update(Section section);
    List<Section> getAll();
    Section findById(Long id);
    void delete(Long id);

}
