-- 問1
SELECT name, salary, hire_date
FROM employees
ORDER BY hire_date DESC;

-- 問2
SELECT e.name, e.salary, e.hire_date
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE d.name = '営業'
  AND e.salary >= 300000;
  
-- 問3
SELECT d.name AS 部門名, COUNT(e.id) AS 従業員数
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
GROUP BY d.name;

-- 問4
SELECT e.name AS 従業員名, d.name AS 部門名
FROM employees e
JOIN departments d ON e.department_id = d.id;

-- 問5
SELECT p.name AS プロジェクト名, COUNT(ep.employee_id) AS 参加人数
FROM projects p
LEFT JOIN employee_projects ep ON p.id = ep.project_id
GROUP BY p.name;

-- 問6
SELECT e.name AS 従業員名, SUM(ep.hours) AS 合計作業時間
FROM employees e
LEFT JOIN employee_projects ep ON e.id = ep.employee_id
GROUP BY e.name
ORDER BY 合計作業時間 DESC;

-- 問7
SELECT d.name AS 部門名, MAX(e.salary) AS 最高給与
FROM departments d
JOIN employees e ON d.id = e.department_id
GROUP BY d.name;

-- 問8
SELECT e.name AS 従業員名
FROM employees e
LEFT JOIN employee_projects ep ON e.id = ep.employee_id
WHERE ep.employee_id IS NULL;
