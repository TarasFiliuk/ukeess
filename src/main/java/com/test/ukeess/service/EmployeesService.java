package com.test.ukeess.service;

import com.test.ukeess.model.Employees;
import com.test.ukeess.request.EmployeesRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeesService {
    Employees findEmployees(int employeesId);
    Employees createEmployees(EmployeesRequest employeesRequest);
    List<Employees> getEmployees();

    Page<Employees> getEmployees(int page);

    Employees update(EmployeesRequest employeesRequest);
    Employees addEmployeesToDepartments(int employerId, int departmentId);
    void deleteEmployees(int employerId);
    List<Employees> searchByName(String name);
}
