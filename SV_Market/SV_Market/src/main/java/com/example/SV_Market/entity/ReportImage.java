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
@Table(name = "report_image")
public class ReportImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String reportImageId;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false, referencedColumnName = "report_id")
    @JsonIgnore
    private Report report;
    private String path;
}
