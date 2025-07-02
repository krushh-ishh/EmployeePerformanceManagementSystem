package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// DTOs
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private LocalDate reviewDate;
    private int score;
    private String reviewComments;
}
