package com.discoveryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class DisccoveryServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisccoveryServerDemoApplication.class, args);
	}

}
