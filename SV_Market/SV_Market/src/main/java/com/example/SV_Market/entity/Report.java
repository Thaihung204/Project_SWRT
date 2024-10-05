package com.example.SV_Market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "report_id")
    private String reportId;
    private String title;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false, referencedColumnName = "userid")
    private User user;
    private String description;
    private String type;
    private String responseMessage;

}
