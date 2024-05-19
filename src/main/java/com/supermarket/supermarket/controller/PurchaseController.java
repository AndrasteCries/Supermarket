package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.ProductRequest;
import com.supermarket.supermarket.dto.PurchaseRequest;
import com.supermarket.supermarket.model.Purchase;
import com.supermarket.supermarket.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("")
    public String getPurchases(Model model){
        model.addAttribute("products", purchaseService.getAllProducts());
        model.addAttribute("purchases", purchaseService.getAll());
        model.addAttribute("purchase", new PurchaseRequest());
        return "purchase/purchase";
    }

    @PostMapping("/create")
    public String createPurchase(@Valid @ModelAttribute("purchase") PurchaseRequest purchaseRequest,
                                 BindingResult result, Model model){
        if (result.hasErrors()) {
            System.out.println(result);
            model.addAttribute("products", purchaseService.getAllProducts());
            model.addAttribute("purchases", purchaseService.getAll());
            model.addAttribute("purchase", new PurchaseRequest());
            return "purchase/purchase";
        }
        Purchase purchase = new Purchase();
        purchase.setDate(LocalDate.now());
        purchase.setProduct(purchaseRequest.getProduct());
        purchase.setCount(purchaseRequest.getCount());

        purchaseService.create(purchase);

        return "redirect:/purchases";
    }
}



