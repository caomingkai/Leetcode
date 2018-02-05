# Write your MySQL query statement below

SELECT department.dept_name,count(student_id) as student_number FROM 
    student RIGHT OUTER JOIN department ON student.dept_id=department.dept_id
GROUP BY department.dept_name
ORDER BY student_number DESC , department.dept_name