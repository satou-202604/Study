package com.example.employee;

import java.time.LocalDate;

/**
 * 契約社員を表すクラス。
 */
public class ContractEmployee extends Employee {

    private int monthlyWage;
    private final int contractMonths;

    public ContractEmployee(String employeeId,
                            String name,
                            String deptId,
                            int monthlyWage,
                            LocalDate hireDate,
                            int contractMonths) {

        super(employeeId,
              name,
              deptId,
              monthlyWage * 12,
              hireDate);

        this.monthlyWage = monthlyWage;
        this.contractMonths = contractMonths;
    }

    public int getMonthlyWage() {
        return monthlyWage;
    }

    public void setMonthlyWage(int monthlyWage) {
        this.monthlyWage = monthlyWage;
        this.annualSalary = monthlyWage * 12;
    }

    public int getContractMonths() {
        return contractMonths;
    }

    /**
     * 契約社員は月額賃金をそのまま返す。
     */
    @Override
    public int calcMonthlySalary() {
        return monthlyWage;
    }

    @Override
    public String getEmployeeType() {
        return "契約社員";
    }
}