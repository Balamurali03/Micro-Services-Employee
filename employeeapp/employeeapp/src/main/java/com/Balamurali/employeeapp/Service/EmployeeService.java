package com.Balamurali.employeeapp.Service;

//import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.Balamurali.employeeapp.Repo.EmployeeRepo;
import com.Balamurali.employeeapp.Response.AddressResponse;
import com.Balamurali.employeeapp.Response.EmployeeResponse;
import com.Balamurali.employeeapp.entity.EmployeeDetails;
import com.Balamurali.employeeapp.openFeignClient.AddressFeignClient;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private ModelMapper modelMapper;
//	@Autowired
//	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient webClient;
//	
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	
//	@Autowired
//	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private AddressFeignClient addressClient;
	
//	public EmployeeService(@Value("${addressservice.base.url}")String addressBaseURL,RestTemplateBuilder builder) {
//		
//		this.restTemplate= builder.rootUri(addressBaseURL).build();
//	}

	
	
	public EmployeeResponse getEmployeeById(int id) {
		
		
		EmployeeDetails employee=employeeRepo.findById(id).get();
		EmployeeResponse employeeResponse =modelMapper.map(employee, EmployeeResponse.class);
		
		
		ResponseEntity<AddressResponse> addressResponseEntity = addressClient.getAddressByEmployeeId(id);
		AddressResponse addressResponse = addressResponseEntity.getBody();
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}
//    private AddressResponse callAddressServiceUsingRestTemplate(int id) {
//    	
////    	List<ServiceInstance> instances = discoveryClient.getInstances("address-app");
////    	ServiceInstance serviceInstance = instances.get(0);
////    	String uri = serviceInstance.getUri().toString();
//    	ServiceInstance serviceInstance = loadBalancerClient.choose("address-app");
//    	String uri = serviceInstance.getUri().toString();
//    	String contextRoot = serviceInstance.getMetadata().get("configPath");
//    	
//    	System.out.println("the uri is------->>>>>>>>"+uri+contextRoot);
//    	
//    	
//    	return  restTemplate.getForObject("http://address-app"+contextRoot+"/address/{id}", AddressResponse.class, id);
//    }
    
//    private AddressResponse callAddressServiceUsingWebClient(int id) {
//    	return webClient.get()
//                .uri("/address/"+id)
//                .retrieve()
//                .bodyToMono(AddressResponse.class)
//                .block();
//    }
	
	public String postEmployeeDetails(EmployeeResponse employeeResponse) {
		
		//EmployeeResponse employeeResponse =modelMapper.map(employee, EmployeeResponse.class);
		
		EmployeeDetails employee= modelMapper.map(employeeResponse, EmployeeDetails.class);
				//new EmployeeDetails();
		
//		employee.setBloodGroup(employeeResponse.getBloodGroup());
//		employee.setEmail(employeeResponse.getEmail());
//		employee.setName(employeeResponse.getName());
//		employee.setId(employeeResponse.getId());
		employeeRepo.save(employee);
		
		AddressResponse addressResponse= employeeResponse.getAddressResponse();
		addressResponse.setEmployeeid(employeeResponse.getId());
		ResponseEntity<String> addressResponseEntity = addressClient.postAddressForEmployee(addressResponse);
		String result = addressResponseEntity.getBody();
		
		
		return "Employee added sucessfully and   "+result;
	}
}
