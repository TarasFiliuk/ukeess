package com.test.ukeess.service.impl;

import com.test.ukeess.model.Departments;
import com.test.ukeess.request.DepartmentsRequest;
import com.test.ukeess.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {
    static Map<Integer,Departments> departmentsMap = new HashMap<>();

    @Override
    public Departments save(Departments departments) {
        return departmentsMap.put(departments.getId(),departments);
    }

    @Override
    public Departments create(DepartmentsRequest departmentsrequest) {
        Departments departments = new Departments();
        departments.setId(departmentsrequest.getId());
        departments.setDepartmentName(departmentsrequest.getDepartmentName());
        return this.save(departments);
    }


    @Override
    public Departments getDepartment(int departmentId) {
        return departmentsMap.get(departmentId);
    }
}
