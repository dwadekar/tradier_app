package com.iit.spring.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class TradierAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradierAppApplication.class, args);
	}

}
