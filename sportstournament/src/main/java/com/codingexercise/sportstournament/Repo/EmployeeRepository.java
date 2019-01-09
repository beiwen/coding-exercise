package com.codingexercise.sportstournament.Repo;

import com.codingexercise.sportstournament.Entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByEmployeeId(Long employeeId);
    Employee findByEmployeeName(String employeeName);
}

