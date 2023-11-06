package com.example.demopockanchana;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@PropertySource({"classpath:application.properties" })
public class DemoPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoPocApplication.class, args);

	}

	@Bean
	public RestTemplate getRestTemplate() {
		// Return a RestTemplat instance which will be used to call other external microservices
		return new RestTemplate();
	}

}


