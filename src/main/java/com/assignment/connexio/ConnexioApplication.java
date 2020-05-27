package com.assignment.connexio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan("com.assignment")
@SpringBootApplication
@EntityScan("com.assignment.entities")
@EnableJpaRepositories("com.assignment.dao")

public class ConnexioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnexioApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}

	@Bean
	public ObjectMapper objectMapper() {
	   // Do any additional configuration here
	   return new ObjectMapper();
	}
	//ObjectMapper mapper = new ObjectMapper();
	
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api").allowedOrigins("http://localhost:4200");
			}
		};
	}
}
