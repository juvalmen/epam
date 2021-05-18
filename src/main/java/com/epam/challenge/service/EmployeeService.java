package com.epam.challenge.service;

import com.epam.challenge.dto.EmployeeTO;

import java.util.List;

public interface EmployeeService {

    void save(final EmployeeTO employeeTO);
    List<EmployeeTO> getEmployees();

}
