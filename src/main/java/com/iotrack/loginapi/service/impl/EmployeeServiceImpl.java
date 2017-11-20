package com.iotrack.loginapi.service.impl;

import com.iotrack.loginapi.domain.Employee;
import com.iotrack.loginapi.repository.EmployeeRepository;
import com.iotrack.loginapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee findOne(Long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }
}
