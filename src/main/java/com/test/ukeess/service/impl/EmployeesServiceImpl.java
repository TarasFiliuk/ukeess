package com.test.ukeess.service.impl;

import com.test.ukeess.exeption.TestNotFoundExeption;
import com.test.ukeess.model.Departments;
import com.test.ukeess.model.Employees;
import com.test.ukeess.request.EmployeesRequest;
import com.test.ukeess.service.DepartmentService;
import com.test.ukeess.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.test.ukeess.model.Employees.Active.*;
import static com.test.ukeess.service.impl.DepartmentServiceImpl.departmentsMap;
import static java.util.Optional.ofNullable;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeesServiceImpl implements EmployeesService {
    private final DepartmentService departmentService;
    Map<Integer, Employees> employeesMap = new HashMap<>();


    @Override
    public Employees findEmployees(int employeesId) {
      return employeesMap.get(employeesId);
    }

    @Override
    public Employees createEmployees(EmployeesRequest employeesRequest) {
        Employees employees = new Employees();
        employees.setEmployeesId(employeesRequest.getEmployeesId());
        employees.setActive(YES);
        employees.setEmployeesName(employeesRequest.getEmployeesName());
        return this.save(employees);
    }

    @Override
    public List<Employees> getEmployees() {
        return this.getAllEmployees();
    }

    @Override
    public Page<Employees> getEmployees(int page) {
        List<Employees> employeesList = getAllEmployees();
        Page<Employees> employees = new PageImpl<>(employeesList,PageRequest.of(page, 2),3);
        return employees;
    }



    @Override
    public Employees update(EmployeesRequest employeesRequest) {
        Employees employees = this.findEmployees(employeesRequest.getEmployeesId());
        ofNullable(employeesRequest.getEmployeesName()).ifPresent(employees::setEmployeesName);
        if (employeesRequest.getEmployeesStatus().equals("NO")) {
            employees.setActive(NO);
        } else employees.setActive(YES);
        if (employeesRequest.getDepartmentId() != 0) {
            Departments departments = departmentsMap.get(employeesRequest.getDepartmentId());
            employees.setEmployeesDepartments(departments);
        }
        return this.save(employees);
    }

    @Override
    public Employees addEmployeesToDepartments(int employerId, int departmentId) {
        Employees employees = this.findEmployees(employerId);
        Departments department = departmentService.getDepartment(departmentId);
        employees.setEmployeesDepartments(department);
        return this.save(employees);
    }

    @Override
    public void deleteEmployees(int employerId) throws TestNotFoundExeption {
        employeesMap.remove(employerId);
    }

    @Override
    public List<Employees> searchByName(String name) {
        List<Employees> employeesList = new ArrayList<>();
        Iterator<Map.Entry<Integer, Employees>> iterator = employeesMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Employees value = iterator.next().getValue();
            if (value.getEmployeesName().equals(name)){
                employeesList.add(value);
            }
        }
        return employeesList;
    }

    private Employees save(Employees employees) {
        return employeesMap.put(employees.getEmployeesId(),employees);
    }

    private List<Employees> getAllEmployees() {
        List<Employees> employeesList = new ArrayList<>();
        Iterator<Map.Entry<Integer, Employees>> iterator = employeesMap.entrySet().iterator();
        while (iterator.hasNext()) {
            employeesList.add(iterator.next().getValue());
        }
        return employeesList;
    }
}
