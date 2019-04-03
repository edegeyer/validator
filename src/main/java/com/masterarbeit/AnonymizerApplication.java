package com.masterarbeit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;

@SpringBootApplication
public class AnonymizerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnonymizerApplication.class, args);
	}
}
