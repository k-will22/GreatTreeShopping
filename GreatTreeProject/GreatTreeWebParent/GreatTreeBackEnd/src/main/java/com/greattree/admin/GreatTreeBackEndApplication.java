package com.greattree.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.greattree.common.entity", "com.greattree.admin.user"})
public class GreatTreeBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreatTreeBackEndApplication.class, args);
	}

}
