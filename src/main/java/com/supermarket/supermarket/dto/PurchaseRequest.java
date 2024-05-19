package com.supermarket.supermarket.dto;

import com.supermarket.supermarket.model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PurchaseRequest {
    private LocalDate date;

    @NotNull
    private BigDecimal count;

    @NotNull
    private Product product;

}
