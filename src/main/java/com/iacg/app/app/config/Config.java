package com.iacg.app.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@Configuration
public class Config {

	@Bean
	Faker faker() {
		return new Faker();
	}
	
	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
}
