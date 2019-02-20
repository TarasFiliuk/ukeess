package com.test.ukeess.service.impl;

import com.test.ukeess.exeption.TestNotFoundExeption;
import com.test.ukeess.model.Departments;
import com.test.ukeess.repository.DepartmentRepository;
import com.test.ukeess.request.DepartmentsRequest;
import com.test.ukeess.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Departments save(Departments departments) {
        return departmentRepository.save(departments);
    }

    @Override
    public Departments create(DepartmentsRequest departmentsrequest) {
        Departments departments = new Departments();
        departments.setDepartmentName(departmentsrequest.getDepartmentName());
        log.info("Department successfully created");
        return this.save(departments);
    }


    @Override
    public Departments getDepartment(int departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new TestNotFoundExeption("Department", format("Department with ID %s not found", departmentId)));
    }
}
