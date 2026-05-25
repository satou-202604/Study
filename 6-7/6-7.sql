問題
問1 従業員名と部署名を一覧で表示する（JOIN）。
回答：
SELECT 
    employees.name,
    departments.name
FROM 
    employees
JOIN 
    departments 
ON employees.department_id = departments.id;


問2 部門ごとの平均給与を表示する。
回答：
SELECT 
    departments.name,
    AVG(employees.salary) AS average_salary
FROM 
    employees
JOIN 
    departments 
ON employees.department_id = departments.id
GROUP BY 
    departments.name;


問3 プロジェクトごとの合計作業時間を表示する。
回答：
SELECT 
    projects.name,
    SUM(employee_projects.hours) AS total_hours

FROM projects

JOIN employee_projects
ON projects.id = employee_projects.project_id

GROUP BY projects.name;


問4 最も給与が高い従業員を表示する（サブクエリ可）。
回答：
SELECT 
    name,
    salary
FROM 
    employees
WHERE 
    salary = (SELECT MAX(salary) FROM employees);


問5 プロジェクトに参加していない従業員を表示する。
回答：
SELECT 
    name
FROM 
    employees
WHERE 
    id NOT IN (SELECT employee_id FROM work_hours);



問6 作業時間が50時間以上の従業員名とプロジェクト名を表示する。
回答：
SELECT
    projects.name,
    SUM(employee_projects.hours) AS total_hours

FROM employee_projects

JOIN projects
ON employee_projects.project_id = projects.id

GROUP BY projects.name;


問7 開発部門の従業員だけを対象に、給与が高い順で表示する。
回答：
SELECT 
    employees.name,
    employees.salary
FROM 
    employees
JOIN 
    departments 
ON employees.department_id = departments.id
WHERE 
    departments.name = '開発'
ORDER BY 
    employees.salary DESC;


問8 従業員ごとの合計作業時間を表示し、時間の多い順に並べる。
回答：
SELECT 
    employees.name,
    SUM(work_hours.hours) AS total_hours
FROM 
    employees
JOIN 
    work_hours 
ON employees.id = work_hours.employee_id
GROUP BY 
    employees.name
ORDER BY 
    total_hours DESC;

