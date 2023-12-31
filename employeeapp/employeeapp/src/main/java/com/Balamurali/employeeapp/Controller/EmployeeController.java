package com.Balamurali.employeeapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Balamurali.employeeapp.Response.EmployeeResponse;
import com.Balamurali.employeeapp.Service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/employees/{id}")
	ResponseEntity <EmployeeResponse> getEmployeeDetails(@PathVariable("id")int id){
		
		EmployeeResponse employeeResponse=employeeService.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	@PostMapping("/employee")
	ResponseEntity <String> postEmployeeDetails(@RequestBody EmployeeResponse employeeResponse ){
		
		String result=employeeService.postEmployeeDetails(employeeResponse);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
