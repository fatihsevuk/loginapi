package com.iotrack.loginapi.service;

import com.iotrack.loginapi.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    Employee findByEmail(String email);
    Employee findOne(Long id);
    void delete(Employee employee);
    List<Employee> getAllEmployee();



}
