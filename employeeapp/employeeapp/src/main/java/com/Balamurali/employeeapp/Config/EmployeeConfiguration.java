package com.Balamurali.employeeapp.Config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeConfiguration {
	@Value("${addressservice.base.url}")
	private String addressBaseURL;
  
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
	@Bean
	public WebClient webClient() {
		return WebClient.builder().baseUrl(addressBaseURL).build();
		
	}
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		 return new RestTemplate();
	}
}
