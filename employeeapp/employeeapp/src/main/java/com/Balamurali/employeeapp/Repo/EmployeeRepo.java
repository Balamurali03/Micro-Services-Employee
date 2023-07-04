package com.Balamurali.employeeapp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Balamurali.employeeapp.entity.EmployeeDetails;

public interface EmployeeRepo extends JpaRepository<EmployeeDetails, Integer>{

}
