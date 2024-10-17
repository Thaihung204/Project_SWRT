package com.example.SV_Market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category_image")
public class CategoryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String categoryImageId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
    @JsonIgnore
    private Category category;

    private String path;
}
