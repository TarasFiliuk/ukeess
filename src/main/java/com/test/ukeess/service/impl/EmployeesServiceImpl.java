package com.test.ukeess.service.impl;

import com.test.ukeess.exeption.TestNotFoundExeption;
import com.test.ukeess.model.Departments;
import com.test.ukeess.model.Employees;
import com.test.ukeess.repository.EmployeesRepository;
import com.test.ukeess.request.EmployeesRequest;
import com.test.ukeess.service.DepartmentService;
import com.test.ukeess.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.test.ukeess.model.Employees.Active.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeesServiceImpl implements EmployeesService {
    private final EmployeesRepository employeesRepository;
    private final DepartmentService departmentService;


    @Override
    public Employees findEmployees(int employeesId) {
        return employeesRepository.findById(employeesId)
                .orElseThrow(() -> new TestNotFoundExeption("Employees", format("Employees with ID %s not found", employeesId)));
    }

    @Override
    public Employees createEmployees(EmployeesRequest employeesRequest) {
        Employees employees = new Employees();
        employees.setActive(YES);
        employees.setEmployeesName(employeesRequest.getEmployeesName());
        log.info("Employees successfully created");
        return this.save(employees);
    }

    @Override
    public List<Employees> getEmployees() {
        return employeesRepository.findAll();
    }

    @Override
    public Employees update(EmployeesRequest employeesRequest) {
        Employees employees = this.findEmployees(employeesRequest.getEmployeesId());
        ofNullable(employeesRequest.getEmployeesName()).ifPresent(employees::setEmployeesName);
        if (employeesRequest.getEmployeesStatus().equals("NO")) {
            employees.setActive(NO);
        }
        Departments department = departmentService.getDepartment(employeesRequest.getDepartmentId());
        employees.setEmployeesDepartments(department);
        log.info("Employees with ID {} updated", employees.getEmployeesId());
        return employeesRepository.save(employees);
    }

    @Override
    public Employees addEmployeesToDepartments(int employerId, int departmentId) {
        Employees employees = this.findEmployees(employerId);
        Departments department = departmentService.getDepartment(departmentId);
        employees.setEmployeesDepartments(department);
        log.info("Employees with ID {} added to department {}", employerId, departmentId);
        return this.save(employees);
    }

    @Override
    public void deleteEmployees(int employerId) {
        Employees employees = this.findEmployees(employerId);
        log.info("Employees with ID {} deleted", employerId);
        employeesRepository.deleteById(employees.getEmployeesId());
    }

    @Override
    public Employees searchByName(String name) {
        return employeesRepository.findByEmployeesName(name);
    }

    private Employees save(Employees employees) {
        return employeesRepository.save(employees);
    }
}
