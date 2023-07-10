package com.Balamurali.employeeapp.openFeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Balamurali.employeeapp.Response.AddressResponse;



@FeignClient(name="ADDRESS-APP", path="/address-app/api/")
public interface AddressFeignClient {
	
	@GetMapping("/address/{employeeId}")
	public ResponseEntity <AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id);
	
	@PostMapping("/address")
	public ResponseEntity <String> postAddressForEmployee(@RequestBody AddressResponse addressResponse);

}
