package org.example.controller;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.example.dto.EmployeeDetailDTO;
import org.example.dto.ReviewDTO;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.PerformanceReview;
import org.example.model.Project;
import org.example.repository.EmployeeRepository;
import org.example.repository.PerformanceReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Controller
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepo;
    private final PerformanceReviewRepository reviewRepo;

//1. Get a list of employees with filters for:
//            - Performance score for a given review_date
//            - Department (should support multiple departments contains filter)
//            - Projects (should support multiple projects contains filter)
// API Call:   (http://localhost:8080/api/employees?score=4&reviewDate=2024-06-01&departments=HR&projects=Apollo)
    @GetMapping
    public List<Employee> getEmployees(
            @RequestParam(required = false) Integer score,
            @RequestParam(required = false) LocalDate reviewDate,
            @RequestParam(required = false) List<String> departments,
            @RequestParam(required = false) List<String> projects
    ) {
        return employeeRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (departments != null && !departments.isEmpty()) {
                Join<Employee, Department> deptJoin = root.join("department");
                for (String d : departments) {
                    predicates.add(cb.like(cb.lower(deptJoin.get("name")), "%" + d.toLowerCase() + "%"));
                }
            }

            if (projects != null && !projects.isEmpty()) {
                Join<Employee, Project> projectJoin = root.join("projects");
                for (String p : projects) {
                    predicates.add(cb.like(cb.lower(projectJoin.get("name")), "%" + p.toLowerCase() + "%"));
                }
            }

            if (score != null && reviewDate != null) {
                Join<Employee, PerformanceReview> reviewJoin = root.join("reviews");
                predicates.add(cb.equal(reviewJoin.get("score"), score));
                predicates.add(cb.equal(reviewJoin.get("reviewDate"), reviewDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

//    2. Fetch detailed employee information, including department, projects, and last 3 performance reviews using id
//    API Call: http://localhost:8080/api/employees/1
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailDTO> getEmployeeDetail(@PathVariable Long id) {
        return employeeRepo.findById(id).map(employee -> {
            EmployeeDetailDTO dto = new EmployeeDetailDTO();
            dto.setName(employee.getName());
            dto.setEmail(employee.getEmail());
            dto.setDepartment(employee.getDepartment().getName());

            List<String> projectNames = employee.getProjects().stream()
                    .map(Project::getName).collect(Collectors.toList());
            dto.setProjects(projectNames);

            List<ReviewDTO> reviews = reviewRepo.findTop3ByEmployeeIdOrderByReviewDateDesc(id).stream()
                    .map(r -> new ReviewDTO(r.getReviewDate(), r.getScore(), r.getReviewComments()))
                    .collect(Collectors.toList());

            dto.setRecentReviews(reviews);

            return ResponseEntity.ok(dto);
        }).orElse(ResponseEntity.notFound().build());
    }
}
