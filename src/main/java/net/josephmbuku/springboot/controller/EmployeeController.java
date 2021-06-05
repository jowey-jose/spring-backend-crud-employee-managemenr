package net.josephmbuku.springboot.controller;

import net.josephmbuku.springboot.exception.ResourceNotFoundException;
import net.josephmbuku.springboot.model.Employee;
import net.josephmbuku.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Get Employee by Id Rest Api
    @GetMapping("/employees/{id}")
    // Path Variable maps the id to the path.
    public ResponseEntity <Employee> getEmployeeById(@PathVariable Long id){
        // Create an employee object.
        // Find By Id returns an optional. An optional has throw exception option.
        // Then throw an exception if record not found.
        // Use a lambda expression with the orElseThrow Exception.
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + id));

        // Return Http Status.
        return ResponseEntity.ok(employee);
    }
}
