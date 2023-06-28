package com.nexttuple.employee.controller;

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
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployee();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable Long id)
    {
        Optional<Employee> employee =employeeService.getEmployeeId(id);
        return employee.map(value-> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee)
    {
        return employeeService.createEmployee(employee);
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
