package com.zagorskidev.rescuecrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration(exclude=ErrorMvcAutoConfiguration.class)
public class RescueCRM {

	public static void main(String[] args) {
		SpringApplication.run(RescueCRM.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder passwortEncoder() {
		return new BCryptPasswordEncoder();
	}
}
