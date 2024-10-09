package com.example.SV_Market.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Upgrade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Upgrade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "upgrade_id", length = 50)
    private String upgradeId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false, referencedColumnName = "userid")
    private User user;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}