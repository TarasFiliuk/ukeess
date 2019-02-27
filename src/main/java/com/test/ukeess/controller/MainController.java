package com.test.ukeess.controller;


import com.test.ukeess.model.Employees;
import com.test.ukeess.request.EmployeesRequest;
import com.test.ukeess.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {
    private final EmployeesService employeesService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    public String getAllEmployees(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Employees> employees = employeesService.getEmployees(page);
        model.addAttribute("data", employees);
        return "hello";
    }

    @PostMapping("/save")
    public String save(EmployeesRequest employeesRequest) {
        Employees employees = employeesService.update(employeesRequest);
        log.info("Employees with ID {} updated", employees.getEmployeesId());
        return "redirect:/hello";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Employees findOne(@RequestParam int id) {
        return employeesService.findEmployees(id);
    }
}
