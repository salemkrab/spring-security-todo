package fr.m2i.todoihm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;


@SpringBootApplication
public class TodoIhmApplication {

	@Bean
	public MessageSource messageSource() {
	  ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	  source.setBasename("messages");
	  return source;
	}
	
	public static void main(String [] args) {
		SpringApplication.run(TodoIhmApplication.class, args);
	}






}
