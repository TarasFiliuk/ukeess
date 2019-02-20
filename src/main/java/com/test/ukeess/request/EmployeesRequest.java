package com.test.ukeess.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeesRequest {
    private int employeesId;
    private String employeesName;
    private String employeesStatus;
    private int departmentId;
}
