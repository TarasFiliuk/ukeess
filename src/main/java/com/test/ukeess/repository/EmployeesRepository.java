package com.test.ukeess.repository;

import com.test.ukeess.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
}
