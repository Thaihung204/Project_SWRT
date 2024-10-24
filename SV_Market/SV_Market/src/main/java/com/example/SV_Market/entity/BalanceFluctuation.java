package com.example.SV_Market.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceFluctuation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "balance_fluctuation_id")
    private String balanceFluctuationId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false, referencedColumnName = "userid")
    @JsonIgnore
    private User user;

    private String transactionType;
    private double amount;
    private double balance;
    private String content;
    private String state;
    private LocalDate date;
}
