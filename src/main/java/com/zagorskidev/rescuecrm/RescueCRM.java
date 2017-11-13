package com.zagorskidev.rescuecrm;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RescueCRM {

	public static void main(String[] args) {
		SpringApplication.run(RescueCRM.class, args);
	}
	
	@Bean
	@Autowired
	public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory) {
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		return sessionFactory;
	}
}
