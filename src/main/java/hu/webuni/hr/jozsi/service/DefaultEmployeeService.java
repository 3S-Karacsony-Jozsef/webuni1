package hu.webuni.hr.jozsi.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import hu.webuni.hr.jozsi.model.Employee;

@Service
@Profile("!smart")
public class DefaultEmployeeService implements EmployeeService {
    @Override
    public int getPayRaisePercent(Employee employee) {
        return 5;
    }
}