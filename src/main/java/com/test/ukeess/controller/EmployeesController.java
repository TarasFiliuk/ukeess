package com.test.ukeess.controller;

import com.test.ukeess.model.Employees;
import com.test.ukeess.request.EmployeesRequest;
import com.test.ukeess.service.EmployeesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.test.ukeess.utils.Constants.INTERNAL_SERVER_ERROR_MESSAGE;
import static com.test.ukeess.utils.Constants.OPERATION_NOT_ALLOWED_MESSAGE;
import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeesService employeesService;


    @ApiOperation("create employees")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Create employees"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PostMapping("/create")
    public ResponseEntity createDepartment(@RequestBody EmployeesRequest employeesRequest) {
        Employees employees = employeesService.createEmployees(employeesRequest);
        return ok().body(employees);
    }

    @ApiOperation("get employees")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "get employees"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/get")
    public ResponseEntity getEmployees() {
        List<Employees> employees = employeesService.getEmployees();
        return ok().body(employees);
    }

    @ApiOperation("update employees")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "update employees"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody EmployeesRequest employeesRequest) {
        Employees employees = employeesService.update(employeesRequest);
        return ok().body(employees);
    }

    @ApiOperation("add employees")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "add employees"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PostMapping("/add")
    public ResponseEntity addEmployeesToDepartment(@RequestBody EmployeesRequest employeesRequest) {
        Employees employees = employeesService.addEmployeesToDepartments(employeesRequest.getEmployeesId(), employeesRequest.getDepartmentId());
        return ok().body(employees);
    }

    @ApiOperation("delete employees")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "delete employees"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @DeleteMapping("/delete/{employeesId}")
    public ResponseEntity deleteEmployees(@PathVariable int employeesId) {
        employeesService.deleteEmployees(employeesId);
        return ok().build();
    }
}
