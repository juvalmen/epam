package com.epam.challenge.mapper;

import com.epam.challenge.dto.EmployeeTO;
import com.epam.challenge.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeTO employeeToEmployeeTO(final Employee employee);
    Employee employeeTOToEmployee(final EmployeeTO employeeTO);

}
