package com.intoThe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationServiceApplication {


	/**
	 * This is the main method of the Spring Boot application.
	 * It launches the application by calling the {@link SpringApplication#run(Class, String[])} method.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

}
