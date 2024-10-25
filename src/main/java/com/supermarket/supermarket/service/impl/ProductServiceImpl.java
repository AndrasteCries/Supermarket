package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.dto.ProductRequest;
import com.supermarket.supermarket.model.Manufacturer;
import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.ProductCategory;
import com.supermarket.supermarket.model.Section;
import com.supermarket.supermarket.repository.ManufacturerRepository;
import com.supermarket.supermarket.repository.ProductCategoryRepository;
import com.supermarket.supermarket.repository.ProductRepository;
import com.supermarket.supermarket.repository.SectionRepository;
import com.supermarket.supermarket.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final SectionRepository sectionRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository categoryRepository,
                          ManufacturerRepository manufacturerRepository, SectionRepository sectionRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Product create(ProductRequest productRequest) {
        ProductCategory category = categoryRepository.findById(productRequest.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Manufacturer manufacturer = manufacturerRepository.findById(productRequest.getManufacturer())
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
        Section section = sectionRepository.findById(productRequest.getSection())
                .orElseThrow(() -> new RuntimeException("Section not found"));

        Product product = new Product();
        return getProduct(productRequest, product, category, manufacturer, section);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id: " + id + " not found"));
    }
    @Override
    public Product update(Long productId, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ProductCategory category = categoryRepository.findById(productRequest.getCategory())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        Manufacturer manufacturer = manufacturerRepository.findById(productRequest.getManufacturer())
                .orElseThrow(() -> new EntityNotFoundException("Manufacturer not found"));
        Section section = sectionRepository.findById(productRequest.getSection())
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));

        return getProduct(productRequest, existingProduct, category, manufacturer, section);
    }
    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    private Product getProduct(ProductRequest productRequest, Product existingProduct, ProductCategory category, Manufacturer manufacturer, Section section) {
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setExpirationDate(productRequest.getExpirationDate());
        existingProduct.setCategory(category);
        existingProduct.setManufacturer(manufacturer);
        existingProduct.setSection(section);

        return productRepository.save(existingProduct);
    }

    @Override
    public Product findByName(String name) {
        return Optional.ofNullable(productRepository.findByName(name))
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    //todo
//    @Override
//    public List<Product> findAllProductByCategory(String category) {
//        return repository.findAllProductByCategory(category);
//    }
}
