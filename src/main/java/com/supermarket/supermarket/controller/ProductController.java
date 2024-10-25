package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.CategoryRequest;
import com.supermarket.supermarket.dto.ProductRequest;
import com.supermarket.supermarket.model.Manufacturer;
import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.ProductCategory;
import com.supermarket.supermarket.model.Section;
import jakarta.validation.Valid;
import com.supermarket.supermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAll();
        List<ProductCategory> categories = productService.getAllCategories();
        List<Manufacturer> manufacturers = productService.getAllManufacturers();
        List<Section> sections = productService.getAllSections();

        model.addAttribute("product", new ProductRequest());
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("sections", sections);

        return "product/product";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        final Product product = productService.getById(id);
        final ProductRequest productRequest = new ProductRequest();
        productRequest.setName(product.getName());

        List<ProductCategory> categories = productService.getAllCategories();
        List<Manufacturer> manufacturers = productService.getAllManufacturers();
        List<Section> sections = productService.getAllSections();

        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("sections", sections);
        model.addAttribute("productId", id);

        model.addAttribute("product", productRequest);
        return "product/edit";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductRequest productRequest,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", productRequest);
            model.addAttribute("categories", productService.getAllCategories());
            model.addAttribute("manufacturers", productService.getAllManufacturers());
            model.addAttribute("sections", productService.getAllSections());
            return "product/product";
        }

        productService.create(productRequest);

        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") ProductRequest productRequest,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", productRequest);
            model.addAttribute("productId", id);
            model.addAttribute("categories", productService.getAllCategories());
            model.addAttribute("manufacturers", productService.getAllManufacturers());
            model.addAttribute("sections", productService.getAllSections());
            return "product/edit";
        }

        Product existingProduct = productService.getById(id);
        productService.update(existingProduct.getId(), productRequest);

        return "redirect:/products";
    }


}
