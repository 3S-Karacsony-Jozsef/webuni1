package hu.webuni.hr.jozsi;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.jozsi.model.Employee;
import hu.webuni.hr.jozsi.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

    @Autowired
    private SalaryService salaryService;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee emp1 = new Employee(1L, "John Doe", "Engineer", 1000, LocalDateTime.now().minusYears(11));
        Employee emp2 = new Employee(2L, "Jane Smith", "Manager", 2000, LocalDateTime.now().minusYears(6));
        Employee emp3 = new Employee(3L, "Bob Brown", "Intern", 500, LocalDateTime.now().minusYears(1));

        System.out.println("Before raise:");
        printEmployee(emp1);
        printEmployee(emp2);
        printEmployee(emp3);

        salaryService.setNewSalary(emp1);
        salaryService.setNewSalary(emp2);
        salaryService.setNewSalary(emp3);

        System.out.println("\nAfter raise:");
        printEmployee(emp1);
        printEmployee(emp2);
        printEmployee(emp3);
    }

    private void printEmployee(Employee e) {
        System.out.println(e.getName() + " - salary: " + e.getSalary());
    }
}