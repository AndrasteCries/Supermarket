package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.ProductInputRequest;
import com.supermarket.supermarket.model.*;
import com.supermarket.supermarket.service.ProductInputSevice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/inputs")
public class ProductInputController  {
    private final ProductInputSevice productInputSevice;

    @Autowired
    public ProductInputController(final ProductInputSevice productInputSevice) {
        this.productInputSevice = productInputSevice;
    }

    @GetMapping("")
    public String getAllProductInputs(Model model) {
        List<ProductInput> productsInputs = productInputSevice.getAll();
        Collections.reverse(productsInputs);

        model.addAttribute("products", productInputSevice.getAllProducts());
        model.addAttribute("suppliers", productInputSevice.getAllSuppliers());
        model.addAttribute("productsInput", new ProductInputRequest());
        model.addAttribute("productsInputs", productsInputs);
        return "productInput/productInput";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductInputRequest productInputRequest,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productInput", productInputRequest);
            model.addAttribute("products", productInputSevice.getAllProducts());
            model.addAttribute("suppliers", productInputSevice.getAllSuppliers());
            return "productInput/productInput";
        }
        ProductInput productInput = new ProductInput();
        productInput.setCount(productInputRequest.getCount());
        productInput.setDate(productInputRequest.getDate());
        productInput.setTime(productInputRequest.getTime());
        productInput.setProduct(productInputRequest.getProduct());
        productInput.setSupplier(productInputRequest.getSupplier());

        productInputSevice.create(productInput);

        return "redirect:/inputs";
    }

    @GetMapping("/edit/{id}")
    public String editProductInput(@PathVariable Long id, Model model) {
        final ProductInput product = productInputSevice.findById(id);
        final ProductInputRequest productInputRequest = new ProductInputRequest();

        productInputRequest.setCount(product.getCount());
        productInputRequest.setDate(product.getDate());

        model.addAttribute("suppliers", productInputSevice.getAllProducts());
        model.addAttribute("products", productInputSevice.getAllSuppliers());
        model.addAttribute("productId", id);

        model.addAttribute("productInput", productInputRequest);
        return "productInput/edit";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") ProductInputRequest productInputRequest,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productInput", productInputRequest);
            model.addAttribute("productInputId", id);
            model.addAttribute("suppliers", productInputSevice.getAllProducts());
            model.addAttribute("products", productInputSevice.getAllSuppliers());
            return "productInput/edit";
        }

        ProductInput existingProduct = productInputSevice.findById(id);
        existingProduct.setCount(productInputRequest.getCount());
        existingProduct.setDate(productInputRequest.getDate());
        existingProduct.setTime(productInputRequest.getTime());
        existingProduct.setProduct(productInputRequest.getProduct());
        existingProduct.setSupplier(productInputRequest.getSupplier());
        productInputSevice.update(existingProduct);

        return "redirect:/products";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteProductInputs(@PathVariable Long id) {
        productInputSevice.delete(id);
        return "redirect:/productInputs";
    }
}
