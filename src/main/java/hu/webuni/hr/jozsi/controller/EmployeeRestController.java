package hu.webuni.hr.jozsi.controller;

import hu.webuni.hr.jozsi.dto.EmployeeDto;
import hu.webuni.hr.jozsi.model.Employee;
import hu.webuni.hr.jozsi.service.EmployeeStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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