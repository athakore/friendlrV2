package com.cooksys.friendlrV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FriendlrV2Application {

	public static void main(String[] args) {
		SpringApplication.run(FriendlrV2Application.class, args);
	}
}
