package com.iotrack.loginapi.repository;

import com.iotrack.loginapi.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee , Long>{



    Employee findByEmail(String email);


}
