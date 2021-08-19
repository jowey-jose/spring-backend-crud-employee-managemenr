package net.josephmbuku.springboot.controller;

import net.josephmbuku.springboot.exception.ResourceNotFoundException;
import net.josephmbuku.springboot.model.Employee;
import net.josephmbuku.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Get Employee by id Rest Api
    @GetMapping("/employees/{id}")
    // Path Variable maps the id to the path.
    public ResponseEntity <Employee> getEmployeeById(@PathVariable Long id){
        // Create an employee object.
        // Find By id returns an optional. An optional has throw exception option.
        // Then throw an exception if record not found.
        // Use a lambda expression with the orElseThrow Exception.
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + id));

        // Return Http Status.
        return ResponseEntity.ok(employee);
    }

    // Update Employee Rest Api
    // Pass id as method argument as well as object of the Employee class.
    @PutMapping("/employees/{id}")// Handles put request for update operations.
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        // First Retrieve the existing employee from the database.
        // Re-using the code to find employee by id, if record not found throws employee not found exception.
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + id));

        // Update the employee variable object with the employeeDetails request object.
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        // Save the updated employee object to the database.
        Employee updatedEmployee = employeeRepository.save(employee);

        // return the Saved and Updated Employee to the client.
        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete Employee Rest Api
    // Returning a deleted Employee Map with status message and Boolean, because otherwise delete method does not return anything.
    @DeleteMapping("/employees/{id}")
    public ResponseEntity  <Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        // First Get the Existing employee by id.
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + id));

        // Pass Employee Object to the delete method.
        // Delete JPA repository method will delete a particular record.
        employeeRepository.delete(employee);

        // Create a map message, to return the deleted message as a true.
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
