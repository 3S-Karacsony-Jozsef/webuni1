package hu.webuni.hr.jozsi.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import hu.webuni.hr.jozsi.config.SalaryConfigProperties;
import hu.webuni.hr.jozsi.model.Employee;

@Service
@Profile("smart")
public class SmartEmployeeService implements EmployeeService {

    @Autowired
    private SalaryConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        double years = ChronoUnit.YEARS.between(employee.getStartDate(), LocalDateTime.now());
        List<Double> limits = config.getLimits();
        List<Integer> percentages = config.getPercentages();

        for (int i = 0; i < limits.size(); i++) {
            if (years >= limits.get(i)) {
                return percentages.get(i);
            }
        }
        return percentages.get(percentages.size() - 1);
    }
}