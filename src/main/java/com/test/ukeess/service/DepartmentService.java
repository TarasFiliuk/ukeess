package com.test.ukeess.service;

import com.test.ukeess.model.Departments;
import com.test.ukeess.request.DepartmentsRequest;

public interface DepartmentService  {
    Departments save(Departments departments);
    Departments create(DepartmentsRequest departmentsrequest);
    Departments getDepartment(int departmentId);
}
