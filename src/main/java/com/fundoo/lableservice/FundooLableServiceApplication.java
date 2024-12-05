package com.fundoo.lableservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FundooLableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooLableServiceApplication.class, args);
		System.out.println("started lable");
	}

}
