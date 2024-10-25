package com.supermarket.supermarket.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product_inputs")
public class ProductInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "count", nullable = false)
    private BigDecimal count;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;
}
