package com.supermarket.supermarket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductRequest {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int expirationDate;

    @NotNull
    private Long category;

    @NotNull
    private Long manufacturer;

    @NotNull
    private Long section;
}