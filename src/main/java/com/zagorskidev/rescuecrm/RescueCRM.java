package com.zagorskidev.rescuecrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Boot  application launcher with beans configuration
 * @author tomek
 *
 */
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
	
	@Bean
	public MailSender mailSender(Environment env) {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		mailSender.setPort(587);
		mailSender.setUsername("apikey");
		mailSender.setPassword(env.getProperty("sendgrid_password"));
		return mailSender;
	}
}
