package de.neuefische.studentwebdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StudentWebDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentWebDbApplication.class, args);
	}

	@Bean
	public RestTemplate createRestTemplate(){
		return new RestTemplate();
	}
}
