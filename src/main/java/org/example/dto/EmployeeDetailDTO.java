package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO {
    private String name;
    private String email;
    private String department;
    private List<String> projects;
    private List<ReviewDTO> recentReviews;
}

