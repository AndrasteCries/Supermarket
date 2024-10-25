package com.supermarket.supermarket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "count", nullable = false)
    private BigDecimal count;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
