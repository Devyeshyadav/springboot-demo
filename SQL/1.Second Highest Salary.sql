select * from employee;

SELECT salary 
FROM employee
ORDER BY salary DESC
OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY;


SELECT TOP 1 salary 
FROM (
    SELECT DISTINCT TOP 2 salary 
    FROM employee 
    ORDER BY salary DESC
) AS t
ORDER BY salary ASC;

select MAX(SALARY) from employee
WHERE SALARY < (select MAX(SALARY) from employee)

SELECT SALARY
FROM (
    SELECT SALARY, DENSE_RANK() OVER (ORDER BY SALARY DESC) AS rnk
    FROM employee
) t
WHERE rnk =2;