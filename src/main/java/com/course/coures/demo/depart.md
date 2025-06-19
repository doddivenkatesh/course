SELECT *
FROM Employee e
WHERE Salary = (
    SELECT MAX(Salary)
    FROM Employee
    WHERE DepartmentId = e.DepartmentId
);


🔹 Task 3: SQL Query — Employees with Highest Salary per Department
Given table:

Id	Name	Salary	DepartmentId
1	Ram	10000	10
2	Sam	15000	10
3	Ravi	15000	10
4	Tom	20000	20

✅ Goal:
Return employees who have the maximum salary within each department, including ties.

✅ SQL Solution (Using Subquery):
sql
Copy
Edit
SELECT *
FROM Employee e
WHERE Salary = (
    SELECT MAX(Salary)
    FROM Employee
    WHERE DepartmentId = e.DepartmentId
);
🧠 Explanation:
The correlated subquery:

sql
Copy
Edit
SELECT MAX(Salary)
FROM Employee
WHERE DepartmentId = e.DepartmentId
gets the maximum salary for each department.

The outer query filters employees whose salary matches the maximum.

🧪 Output from Query:
Id	Name	Salary	DepartmentId
2	Sam	15000	10
3	Ravi	15000	10
4	Tom	20000	20

✅ Sam and Ravi both tied with the max salary in Dept 10.

🔹 Server-Side Pagination in SQL
To paginate results from the server side, use LIMIT and OFFSET (in MySQL, PostgreSQL, etc.)

✅ Example Query:
sql
Copy
Edit
SELECT * FROM Employee
ORDER BY Id
LIMIT 10 OFFSET 20;
LIMIT 10: Return 10 records

OFFSET 20: Skip first 20 records

Useful for page 3 (assuming 10 records per page)

✅ Why Pagination is Important in Production APIs:
Reason	Explanation
✅ Performance	Reduces DB load by fetching only what's needed.
✅ Scalability	Handles large datasets without overloading the client/server.
✅ User Experience (UX)	Faster page load times, better response times.
✅ Bandwidth Efficiency	Sends only necessary data over network.
✅ Avoids Timeouts	Large result sets might crash or timeout without pagination.