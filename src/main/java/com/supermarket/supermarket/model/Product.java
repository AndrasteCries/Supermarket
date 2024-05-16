package com.supermarket.supermarket.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {
    private String name;
    private int price;
    private String expire_date;

}
