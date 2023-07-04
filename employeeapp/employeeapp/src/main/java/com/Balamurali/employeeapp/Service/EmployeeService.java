package com.Balamurali.employeeapp.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Balamurali.employeeapp.Repo.EmployeeRepo;
import com.Balamurali.employeeapp.Response.AddressResponse;
import com.Balamurali.employeeapp.Response.EmployeeResponse;
import com.Balamurali.employeeapp.entity.EmployeeDetails;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${addressservice.base.url}")
	private String addressBaseURL;
	
	public EmployeeResponse getEmployeeById(int id) {
		
		
		EmployeeDetails employee=employeeRepo.findById(id).get();
		EmployeeResponse employeeResponse =modelMapper.map(employee, EmployeeResponse.class);
		AddressResponse addressResponse = restTemplate.getForObject(addressBaseURL+"/address/{id}", AddressResponse.class, id);
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}

}
