package com.test.ukeess.controller;

import com.test.ukeess.model.Departments;
import com.test.ukeess.request.DepartmentsRequest;
import com.test.ukeess.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import static com.test.ukeess.utils.Constants.INTERNAL_SERVER_ERROR_MESSAGE;
import static com.test.ukeess.utils.Constants.OPERATION_NOT_ALLOWED_MESSAGE;
import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @ApiOperation("create department")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Create department"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PostMapping("/create")
    public ResponseEntity createDepartment(@RequestBody DepartmentsRequest departmentsRequest) {
        Departments departments = departmentService.create(departmentsRequest);
        log.info("Department successfully created");
        return ok().body(departments);
    }
}
