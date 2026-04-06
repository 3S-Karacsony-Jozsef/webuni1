package hu.webuni.hr.jozsi.service;

import hu.webuni.hr.jozsi.model.Employee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeStorageService {
    private final List<Employee> employees = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    public Optional<Employee> findById(Long id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(idGenerator.getAndIncrement());
        }
        employees.add(employee);
        return employee;
    }

    public Optional<Employee> update(Employee updated) {
        return findById(updated.getId()).map(existing -> {
            existing.setName(updated.getName());
            existing.setPosition(updated.getPosition());
            existing.setSalary(updated.getSalary());
            existing.setStartDate(updated.getStartDate());
            return existing;
        });
    }

    public boolean deleteById(Long id) {
        return employees.removeIf(e -> e.getId().equals(id));
    }
}