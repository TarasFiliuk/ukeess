package com.test.ukeess.repository;

import com.test.ukeess.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Departments, Integer> {
}
