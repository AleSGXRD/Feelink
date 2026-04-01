package com.sigith.feelink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FeelinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeelinkApplication.class, args);
	}

}
