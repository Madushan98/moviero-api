package com.webproject.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMovieApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCrptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}



}
