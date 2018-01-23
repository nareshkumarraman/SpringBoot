package com.security.oauth.oauthdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
public class OauthDemoApplication extends SpringServletContainerInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OauthDemoApplication.class, args);
		System.out.println("Added master");
		System.out.println("branch");
		System.out.println("Added master one");
		System.out.println("branchone changes");
		System.out.println("Branch one new changes");
		System.out.println("Branch one new first changes");
	}
}
