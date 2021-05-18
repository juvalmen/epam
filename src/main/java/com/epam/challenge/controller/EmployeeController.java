package com.epam.challenge.controller;

import com.epam.challenge.dto.EmployeeTO;
import com.epam.challenge.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeTO request) {
        employeeService.save(request);
        log.info("Employee save sucessfully with body {} ", request);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        final List<EmployeeTO> employeeTOList = employeeService.getEmployees();
        return ResponseEntity.ok(employeeTOList);
    }

}
