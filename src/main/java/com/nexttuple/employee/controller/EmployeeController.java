package com.nexttuple.employee.controller;

import com.nexttuple.employee.exception.ResourceNotFoundException;
import com.nexttuple.employee.model.Employee;
import com.nexttuple.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee> > getAllEmployees()
    {
        List<Employee> employees=employeeService.getAllEmployee();
        if(employees.isEmpty())
        {

            throw new ResourceNotFoundException("No employees found.", "There are no employees in the system.");
        }
        else {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable Long id)
    {
        Optional<Employee> employee = employeeService.getEmployeeId(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("No employee found.", "There are no employees with the given ID."));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {   Employee newEmployee=employeeService.createEmployee(employee);
        return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee)
    {
        Optional<Employee> existingEmployee= employeeService.getEmployeeId(id);
        if(existingEmployee.isPresent()) {
            Employee updateEmployee=employeeService.updateEmployee(id,employee);
            return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
        }
        else
        {
            throw new ResourceNotFoundException("No employees found.", "There are no employees in the system.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id)
    {
        Optional<Employee> existingEmployee= employeeService.getEmployeeId(id);
        if(existingEmployee.isPresent())
        {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            throw new ResourceNotFoundException("No employees found.", "There are no employees in the system.");


    }

    @RequestMapping(value = "/**")
    public ResponseEntity<Void> handleInvalidRequest() {
        throw new ResourceNotFoundException("Invalid request.", "The requested URL is not valid.");
    }
}
