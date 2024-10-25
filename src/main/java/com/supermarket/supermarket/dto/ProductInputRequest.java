package com.supermarket.supermarket.dto;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ProductInputRequest {
    @NotNull
    private Product product;
    @NotNull
    private Supplier supplier;
    @NotNull
    private BigDecimal count;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;

}

