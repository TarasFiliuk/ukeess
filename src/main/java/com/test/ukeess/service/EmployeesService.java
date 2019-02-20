package com.test.ukeess.service;

import com.test.ukeess.model.Employees;
import com.test.ukeess.request.EmployeesRequest;

import java.util.List;

public interface EmployeesService {
    Employees findEmployees(int employeesId);
    Employees createEmployees(EmployeesRequest employeesRequest);
    List<Employees> getEmployees();
    Employees update(EmployeesRequest employeesRequest);
    Employees addEmployeesToDepartments(int employerId, int departmentId);
    void deleteEmployees(int employerId);
}
