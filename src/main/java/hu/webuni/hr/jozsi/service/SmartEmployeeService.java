package hu.webuni.hr.jozsi.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import hu.webuni.hr.jozsi.model.Employee;

@Service
@Profile("smart")
public class SmartEmployeeService implements EmployeeService {
    @Override
    public int getPayRaisePercent(Employee employee) {
        double years = ChronoUnit.YEARS.between(employee.getStartDate(), LocalDateTime.now());
        
        return years >= 10 ? 10
            : years >= 5  ? 5
            : years >= 2.5 ? 2
            : 0;
    }
}