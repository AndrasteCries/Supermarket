package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.ProductPromotionRequest;
import com.supermarket.supermarket.dto.PromotionRequest;
import com.supermarket.supermarket.dto.SectionRequest;
import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.ProductPromotion;
import com.supermarket.supermarket.model.Promotion;
import com.supermarket.supermarket.model.Section;
import com.supermarket.supermarket.service.ProductPromotionService;
import com.supermarket.supermarket.service.ProductService;
import com.supermarket.supermarket.service.PromotionService;
import com.supermarket.supermarket.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("promotions")
public class PromotionController {
    private final PromotionService promotionService;
    private final ProductService productService;
    private final ProductPromotionService productPromotionService;

    public PromotionController(PromotionService promotionService, ProductPromotionService productPromotionService,
                               ProductService productService) {
        this.productService = productService;
        this.promotionService = promotionService;
        this.productPromotionService = productPromotionService;
    }

    @GetMapping("")
    public String getAllPromotion(Model model) {
        List<Promotion> promotions = promotionService.getAll();
        model.addAttribute("promotion", new PromotionRequest());
        model.addAttribute("promotions", promotions);
        return "promotion/promotion";
    }

    @GetMapping("/{id}")
    public String getPromotion(@PathVariable Long id, Model model) {
        Promotion promotion = promotionService.findById(id);
        model.addAttribute("promotion", promotion);
        model.addAttribute("products", promotionService.getAllProducts());
        model.addAttribute("promotions", productPromotionService.findAllByPromotion(id));
        model.addAttribute("dpromotions", promotionService.getAll());
        model.addAttribute("productPromotion", new ProductPromotionRequest());
        return "promotion/show";
    }

    @PostMapping("/product_promotions/create")
    public String createProductPromotion(@Valid @ModelAttribute("productPromotion") ProductPromotionRequest productPromotionRequest,
                                         BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "redirect:/promotions";
        }

        ProductPromotion productPromotion = new ProductPromotion();
        productPromotion.setProduct(productPromotionRequest.getProduct());
        productPromotion.setPromotion(productPromotionRequest.getPromotion());
        productPromotion.setPercent(productPromotionRequest.getPercent());

        productPromotionService.create(productPromotion);
        return "redirect:/promotions/" + productPromotionRequest.getPromotion().getId();
    }

    @PostMapping("/create")
    public String createPromotion(@Valid @ModelAttribute("promotion") PromotionRequest promotionRequest,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("promotion", promotionRequest);
            return "promotion/promotion";
        }
        final Promotion promotion = new Promotion();
        promotion.setName(promotionRequest.getName());
        promotion.setStartDate(promotionRequest.getStartDate());
        promotion.setEndDate(promotionRequest.getEndDate());
        promotionService.create(promotion);
        return "redirect:/promotions";
    }

    @GetMapping("/edit/{id}")
    public String editPromotion(@PathVariable Long id, Model model) {
        final Promotion promotion = promotionService.findById(id);
        final PromotionRequest promotionRequest = new PromotionRequest();
        promotionRequest.setName(promotion.getName());
        promotionRequest.setStartDate(promotion.getStartDate());
        promotionRequest.setEndDate(promotion.getStartDate());
        model.addAttribute("promotion", promotionRequest);
        model.addAttribute("promotionId", id);
        return "promotion/edit";
    }

    @PostMapping("/update/{id}")
    public String updateSection(@PathVariable Long id, @Valid @ModelAttribute("promotion")
    PromotionRequest promotionRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("promotion", promotionRequest);
            model.addAttribute("promotionId", id);
            return "promotion/edit";
        }
        final Promotion promotion = promotionService.findById(id);
        promotion.setName(promotionRequest.getName());
        promotionService.update(promotion);
        return "redirect:/promotions";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSection(@PathVariable Long id) {
        promotionService.delete(id);
        return "redirect:/promotions";
    }
}
