package com.dgtic.unam.register_image_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class RegisterImageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterImageServiceApplication.class, args);
	}

}
