package com.project.flightsinformationsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightsInformationSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FlightsInformationSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Program Executed Successfully...");
	
	}
}

