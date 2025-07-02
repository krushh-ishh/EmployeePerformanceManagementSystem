-- Departments
INSERT INTO department (id, name, budget) VALUES (1, 'HR', 100000), (2, 'IT', 200000);

-- Projects
INSERT INTO project (id, name, start_date, end_date, department_id) VALUES
(1, 'Apollo', '2024-01-01', '2024-12-31', 1),
(2, 'Zeus', '2024-03-01', '2024-11-30', 2);

-- Employees
INSERT INTO employee (id, name, email, date_of_joining, salary, department_id, manager_id) VALUES
(1, 'Alice', 'alice@example.com', '2022-01-10', 60000, 1, null),
(2, 'Bob', 'bob@example.com', '2023-02-15', 50000, 2, 1);

-- Employee_Project (join table)
INSERT INTO employee_project (employee_id, project_id) VALUES (1, 1), (2, 2);

-- Performance Reviews
INSERT INTO performance_review (id, employee_id, review_date, score, review_comments) VALUES
(1, 1, '2024-06-01', 4, 'Excellent work'),
(2, 1, '2024-05-01', 5, 'Outstanding'),
(3, 1, '2024-04-01', 3, 'Satisfactory'),
(4, 2, '2024-06-01', 2, 'Needs improvement');
