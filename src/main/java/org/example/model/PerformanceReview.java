package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

// Entity: PerformanceReview
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReview {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate reviewDate;
    private Integer score;
    private String reviewComments;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
