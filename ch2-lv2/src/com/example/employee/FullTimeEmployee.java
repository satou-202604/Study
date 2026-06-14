package com.example.employee;

import java.time.LocalDate;

public class FullTimeEmployee extends Employee implements Position {

    private final String positionName;
    private final int allowance;
    private final int retirementBonusRate;

    public FullTimeEmployee(String employeeId, String name, String deptId,
                            int annualSalary, LocalDate hireDate,
                            String positionName, int allowance,
                            int retirementBonusRate) {

        super(employeeId, name, deptId, annualSalary, hireDate);

        this.positionName = positionName;
        this.allowance = allowance;
        this.retirementBonusRate = retirementBonusRate;
    }

    @Override
    public int calcMonthlySalary() {
        return annualSalary / 12 + allowance;
    }

    @Override
    public String getPositionName() {
        return positionName;
    }

    @Override
    public int getAllowance() {
        return allowance;
    }

    public int calcRetirementBonus() {
        return calcMonthlySalary() * retirementBonusRate;
    }

    @Override
    public String getEmployeeType() {
        return "正社員";
    }
}