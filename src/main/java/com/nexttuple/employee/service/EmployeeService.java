package com.nexttuple.employee.service;

import com.nexttuple.employee.model.Employee;
import com.nexttuple.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private  EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();

    }

    public Optional<Employee> getEmployeeId(Long id)
    {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Long id, Employee employee)
    {
        employee.setId(id); // setting the id of the employee object to the specified id value.
       return employeeRepository.save(employee); //  the ID will be used to determine whether to perform an update or an insert operation.
                                                // if no existing employee with given ID is found, it creates a new one
    }
    public void deleteEmployee(Long id)
    {
        employeeRepository.deleteById(id);
    }


}
