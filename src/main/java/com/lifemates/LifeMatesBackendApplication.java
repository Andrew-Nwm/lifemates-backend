package com.lifemates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.lifemates"})
public class LifeMatesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeMatesBackendApplication.class, args);
	}
}
