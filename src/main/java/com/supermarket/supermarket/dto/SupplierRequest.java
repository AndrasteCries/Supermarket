package com.supermarket.supermarket.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequest {
    @NotBlank
    private String name;
}
