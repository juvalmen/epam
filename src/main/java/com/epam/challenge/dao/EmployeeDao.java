package com.epam.challenge.dao;

import com.epam.challenge.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository <Employee, Long> {

}
