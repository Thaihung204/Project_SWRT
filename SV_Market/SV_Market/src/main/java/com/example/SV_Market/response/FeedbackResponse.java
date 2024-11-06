package com.example.SV_Market.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class FeedbackResponse {
    private String feedbackId;
    private UserResponse sender;
    private UserResponse receiver;

    private int rating;
    private String description;
    private LocalDate createdAt;
}
