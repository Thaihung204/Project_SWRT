package com.example.SV_Market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    private String categoryId;
    private String title;
    private String description;
    private String image;

//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
//    @JsonIgnore
//    private List<Product> product;
}
