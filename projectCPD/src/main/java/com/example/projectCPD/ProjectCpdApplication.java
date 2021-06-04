package com.example.projectCPD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class ProjectCpdApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ProjectCpdApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProjectCpdApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
		try {
			Application2.startApp2();
		} catch (IOException e) {
			System.out.println("Connection lost");
		}


	}

}
