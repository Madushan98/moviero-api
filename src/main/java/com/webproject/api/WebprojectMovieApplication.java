package com.webproject.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;





@SpringBootApplication
public class WebprojectMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebprojectMovieApplication.class, args);
		
		
	}


	@Bean
	public BCryptPasswordEncoder bCrptPasswordEncoder() {
		
		
		return new BCryptPasswordEncoder();
		
	}
	
	
	
	@Bean 
	public SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
	}
	
	

}
