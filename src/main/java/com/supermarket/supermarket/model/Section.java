package com.supermarket.supermarket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
