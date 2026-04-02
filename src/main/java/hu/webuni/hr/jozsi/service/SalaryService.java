package hu.webuni.hr.jozsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.jozsi.model.Employee;

@Service
public class SalaryService {

    @Autowired
    private EmployeeService employeeService;

    public void setNewSalary(Employee employee) {
        int raisePercent = employeeService.getPayRaisePercent(employee);
        int currentSalary = employee.getSalary();
        int newSalary = currentSalary + (currentSalary * raisePercent / 100);
        employee.setSalary(newSalary);
    }
}