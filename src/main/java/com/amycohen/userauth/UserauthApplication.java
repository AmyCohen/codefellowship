package com.amycohen.userauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserauthApplication.class, args);

		System.out.println("http://localhost:8080");
	}

}

