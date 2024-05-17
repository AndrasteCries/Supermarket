package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.CategoryRequest;
import com.supermarket.supermarket.model.ProductCategory;
import com.supermarket.supermarket.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("categories")
public class ProductCategoryController {
    private final CategoryService categoryService;

    public ProductCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getAllCategories(Model model) {
        List<ProductCategory> categories = categoryService.getAll();
        model.addAttribute("category", new CategoryRequest());
        model.addAttribute("categories", categories);
        return "category/category";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        final ProductCategory productCategory = categoryService.findById(id);
        final CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName(productCategory.getName());
        model.addAttribute("category", categoryRequest);
        model.addAttribute("categoryId", id);
        return "category/edit";
    }

    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute("category") CategoryRequest categoryRequest,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", categoryRequest);
            return "category/category";
        }
        final ProductCategory productCategory = new ProductCategory();
        productCategory.setName(categoryRequest.getName());
        categoryService.create(productCategory);
        return "redirect:/categories";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @Valid @ModelAttribute("category") CategoryRequest categoryRequest,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", categoryRequest);
            model.addAttribute("categoryId", id);
            return "category/edit";
        }
        final ProductCategory productCategory = categoryService.findById(id);
        productCategory.setName(categoryRequest.getName());
        categoryService.update(productCategory);
        return "redirect:/categories";
    }
}
