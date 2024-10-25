package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.ProductInputRequest;
import com.supermarket.supermarket.dto.ProductPromotionRequest;
import com.supermarket.supermarket.model.ProductInput;
import com.supermarket.supermarket.model.ProductPromotion;
import com.supermarket.supermarket.service.ProductPromotionService;
import com.supermarket.supermarket.service.impl.ProductPromotionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/product_promotion")
public class ProductPromotionController {

    private final ProductPromotionService productPromotionService;


    public ProductPromotionController(ProductPromotionService productPromotionService) {
        this.productPromotionService = productPromotionService;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductInputs(@PathVariable Long id) {
        Long p_id = productPromotionService.findById(id).getPromotion().getId();
        productPromotionService.delete(id);
        return "redirect:/promotions/" + p_id;
    }

//    @PostMapping("/create")
//    public String createProduct(@Valid @ModelAttribute("productPromotion") ProductPromotionRequest productPromotionRequest,
//                                BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("productInput", productPromotionRequest);
//            model.addAttribute("products", productPromotionService.getAllProducts());
//            model.addAttribute("promotions", productPromotionService.getAllPromotion());
//            return "promotion/promotion";
//        }
//
//        ProductInput productInput = new ProductInput();
//        productInput.setCount(productInputRequest.getCount());
//        productInput.setDate(productInputRequest.getDate());
//        productInput.setTime(productInputRequest.getTime());
//        productInput.setProduct(productInputRequest.getProduct());
//        productInput.setSupplier(productInputRequest.getSupplier());
//
//        productInputSevice.create(productInput);
//
//        return "redirect:/inputs";
//    }




}
