package net.josephmbuku.springboot.controller;

import net.josephmbuku.springboot.model.Employee;
import net.josephmbuku.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    //  Inject Employee Repository.
    @Autowired // Will inject employees using spring container.
    private EmployeeRepository employeeRepository;

    // Get All Employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // Create Employee Rest Api
    @PostMapping(value = "/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

}
