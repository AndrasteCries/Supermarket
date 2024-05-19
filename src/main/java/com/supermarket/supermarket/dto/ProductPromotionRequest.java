package com.supermarket.supermarket.dto;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.Promotion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductPromotionRequest {
    @NotNull
    private Product product;
    @NotNull
    private Long promotion;
    @NotNull
    private BigDecimal percent;

}
