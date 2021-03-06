package com.manos.spring5recipeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.manos")
public class Spring5RecipeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5RecipeAppApplication.class, args);
	}
}
