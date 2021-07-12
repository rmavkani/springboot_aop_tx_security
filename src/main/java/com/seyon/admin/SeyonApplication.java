package com.seyon.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagement
public class SeyonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeyonApplication.class, args);
	}
	
}
