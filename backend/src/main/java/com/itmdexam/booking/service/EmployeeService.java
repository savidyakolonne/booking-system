package com.itmdexam.booking.service;

import com.itmdexam.booking.dto.EmployeeRequest;
import com.itmdexam.booking.entity.Employee;
import com.itmdexam.booking.exception.ResourceNotFoundException;
import com.itmdexam.booking.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public Employee createEmployee(EmployeeRequest request){

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeRequest request){

        Employee employee = getEmployeeById(id);

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);
    }
}
