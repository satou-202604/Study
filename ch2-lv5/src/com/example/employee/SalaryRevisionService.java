package com.example.employee;

import java.util.List;

/**
 * 給与改定業務ロジックを集約するサービスクラス。
 */
public class SalaryRevisionService {

    private final EmployeeRepository repository;

    public SalaryRevisionService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * 全従業員に一律の昇給率を適用する。
     *
     * @param raiseRate 昇給率（例: 0.08 = 8%アップ）
     */
    public void applyRaise(double raiseRate) {

        for (Employee emp : repository.findAllEmployees()) {

            // 正社員
            if (emp instanceof FullTimeEmployee) {

                FullTimeEmployee ft = (FullTimeEmployee) emp;

                int beforeMonthly = ft.calcMonthlySalary();

                int afterMonthly =
                        (int) (beforeMonthly * (1.0 + raiseRate));

                // 手当を除いた年俸部分を更新
                int newAnnualSalary =
                        (afterMonthly - ft.getAllowance()) * 12;

                ft.updateAnnualSalary(newAnnualSalary);

            // 契約社員
            } else if (emp instanceof ContractEmployee) {

                ContractEmployee ce = (ContractEmployee) emp;

                int newMonthlyWage =
                        (int) (ce.getMonthlyWage() * (1.0 + raiseRate));

                ce.setMonthlyWage(newMonthlyWage);

                ce.updateAnnualSalary(newMonthlyWage * 12);

            // その他の従業員
            } else {

                int newAnnualSalary =
                        (int) (emp.getAnnualSalary() * (1.0 + raiseRate));

                emp.updateAnnualSalary(newAnnualSalary);
            }
        }
    }

    /**
     * 給与改定レポートを表示する。
     *
     * @param beforeSalaries 改定前の月額給与配列
     */
    public void printRevisionReport(int[] beforeSalaries) {

        List<Employee> employees = repository.findAllEmployees();

        System.out.println("===== 給与改定レポート =====");

        for (int i = 0; i < employees.size(); i++) {

            Employee emp = employees.get(i);

            int before = beforeSalaries[i];
            int after = emp.calcMonthlySalary();
            int diff = after - before;

            System.out.printf(
                    "[%s] %-10s  改定前：%,d円 → 改定後：%,d円  増減：%+,d円%n",
                    emp.getEmployeeId(),
                    emp.getName(),
                    before,
                    after,
                    diff
            );
        }
    }

    /**
     * 部署別給与合計を表示する。
     */
    public void printDeptSummary() {

        List<Department> departments = repository.findAllDepartments();

        String[] deptIds = new String[departments.size()];
        int[] deptTotals = new int[departments.size()];

        for (int i = 0; i < departments.size(); i++) {
            deptIds[i] = departments.get(i).getDeptId();
        }

        for (Employee emp : repository.findAllEmployees()) {

            for (int i = 0; i < deptIds.length; i++) {

                if (deptIds[i].equals(emp.getDeptId())) {
                    deptTotals[i] += emp.calcMonthlySalary();
                    break;
                }
            }
        }

        System.out.println("===== 部署別給与集計 =====");

        for (int i = 0; i < deptTotals.length; i++) {

            Department dept = departments.get(i);

            System.out.printf(
                    "%s（%s）  月額合計：%,d円%n",
                    dept.getDeptName(),
                    dept.getLocation(),
                    deptTotals[i]
            );
        }
    }

    /**
     * 平均月額給与を返す。
     *
     * @return 平均月額給与
     */
    public double calcAverageMonthlySalary() {

        List<Employee> employees = repository.findAllEmployees();

        if (employees.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (Employee emp : employees) {
            total += emp.calcMonthlySalary();
        }

        return (double) total / employees.size();
    }

    /**
     * 従業員数を返す。
     *
     * @return 従業員数
     */
    public int getEmployeeCount() {
        return repository.countEmployees();
    }
}