package com.epam.challenge.impl;

import com.epam.challenge.dao.EmployeeDao;
import com.epam.challenge.dto.EmployeeTO;
import com.epam.challenge.mapper.EmployeeMapper;
import com.epam.challenge.model.Employee;
import com.epam.challenge.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void save(final EmployeeTO employeeTO) {
        final Employee employee = new Employee();
        employee.setId(employeeTO.getId());
        employee.setEmail(employeeTO.getEmail());
        employee.setName(employeeTO.getName());
        employee.setLastName(employeeTO.getLastName());
        employeeDao.save(employee);
        log.info("Save sucessfully");
    }

    @Override
    public List<EmployeeTO> getEmployees() {
        log.info("Getting all Employees");
       return employeeDao.findAll().stream().map(employee -> EmployeeTO.builder()
               .id(employee.getId())
               .email(employee.getEmail())
               .name(employee.getName())
               .lastName(employee.getLastName()).build()).collect(Collectors.toList());
    }
}
