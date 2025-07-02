
Task: Data Retrieval in Employee Performance Management System
Objective: Build a REST API to pull data from provided schema using spring boot. DatabaseSchema: BuildthisschemausingPOJOs/models,etc.
Entity
Columns
Employee
id (PK), name, email (unique), department_id (FK), date_of_joining, salary, manager_id (FK to Employee.id)
Department
id (PK), name, budget
PerformanceReview
id (PK), employee_id (FK), review_date, score, review_comments
Project
id (PK), name, start_date, end_date, department_id (FK)
Employee_Project (Many-to-Many)
employee_id (FK), project_id (FK), assigned_date, role
Task:
Develop following REST endpoints to fetch data with filters.
1. Get a list of employees with filters for:
- Performance score for a given review_date
- Department (should support multiple departments contains filter) - Projects (should support multiple projects contains filter)
2. Fetch detailed employee information, including department, projects, and last 3 performance reviews using id.
