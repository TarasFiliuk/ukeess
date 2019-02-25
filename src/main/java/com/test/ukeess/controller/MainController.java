package com.test.ukeess.controller;


import com.test.ukeess.model.Employees;
import com.test.ukeess.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {
    private final EmployeesService employeesService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    public String getAllEmployees(Model model) {
        List<Employees> employees = employeesService.getEmployees();
        model.addAttribute("employees", employees);
        return "hello";
    }

}
