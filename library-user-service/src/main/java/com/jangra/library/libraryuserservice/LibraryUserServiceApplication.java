package com.jangra.library.libraryuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.jangra.library.libraryuserservice")
@EnableDiscoveryClient
public class LibraryUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryUserServiceApplication.class, args);
	}

}
