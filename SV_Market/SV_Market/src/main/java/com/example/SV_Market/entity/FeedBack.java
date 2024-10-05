package com.example.SV_Market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "feedback")
public class FeedBack {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String feedbackId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;  // Feedback is associated with one product

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    private int rating;
    private String description;

    @Column(name = "created_at")
    private LocalDate createdAt;
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private Long feedbackId;
//
////    @ManyToOne
////    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
//
//
//    private User sender;
//    private User receiver;
//    private String productId;
//    private int rating;
//    private String description;
//    private LocalDate createdAt;
    
}