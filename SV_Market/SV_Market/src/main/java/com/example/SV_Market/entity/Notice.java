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
@Table(name = "notice")
public class    Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String noticeId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false, referencedColumnName = "userid")
    private User user;

    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private LocalDate createAt;

}
