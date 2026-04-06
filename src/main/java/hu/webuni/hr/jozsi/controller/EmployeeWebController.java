package hu.webuni.hr.jozsi.controller;

import hu.webuni.hr.jozsi.model.Employee;
import hu.webuni.hr.jozsi.service.EmployeeStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    @Autowired
    private EmployeeStorageService storage;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", storage.findAll());
        return "employees";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee) {
        storage.save(employee);
        return "redirect:/employees";
    }
}