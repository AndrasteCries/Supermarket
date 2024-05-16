package com.supermarket.supermarket.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private int price;
    private LocalDateTime expire_date;
}
