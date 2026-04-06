package hu.webuni.hr.jozsi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.jozsi.dto.EmployeeDto;
import hu.webuni.hr.jozsi.model.Employee;
import hu.webuni.hr.jozsi.service.EmployeeStorageService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeStorageService storage;

    private EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getPosition(), employee.getSalary(), employee.getStartDate());
    }

    private Employee toEntity(EmployeeDto dto) {
        return new Employee(dto.getId(), dto.getName(), dto.getPosition(), dto.getSalary(), dto.getStartDate());
    }

    @GetMapping
    public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary) {
        List<Employee> employees = storage.findAll();
        if (minSalary != null) {
            employees = employees.stream().filter(e -> e.getSalary() >= minSalary).collect(Collectors.toList());
        }
        return employees.stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        Employee employee = storage.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDto(employee);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto create(@RequestBody EmployeeDto dto) {
        Employee saved = storage.save(toEntity(dto));
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody EmployeeDto dto) {
        dto.setId(id);
        Employee updated = storage.update(toEntity(dto)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!storage.deleteById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}