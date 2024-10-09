package com.example.SV_Market.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false, referencedColumnName = "userid")
    private User user;


    private String productName;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;

    private int quantity;
    private long price;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
    @JsonIgnore
    private Category category;

    private String type; // sell/exchange/se
    private String state; //new/used
    private String status; //doiduyet/daduyet-dangban/daduyet-an/fail
    private LocalDate create_at;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedBack> feedBacks;

}
