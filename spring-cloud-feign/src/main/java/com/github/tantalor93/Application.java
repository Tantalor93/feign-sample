package com.github.tantalor93;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.github.tantalor93.client.DynamicHostClient;
import com.github.tantalor93.client.TestClient;

@SpringBootApplication
@EnableFeignClients
public class Application implements CommandLineRunner {

	@Autowired
	private TestClient client;

	@Autowired
	private DynamicHostClient dynamicHostClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		String message = client.getMessage();

		System.out.println(message);

		String message1 = dynamicHostClient.getMessage(URI.create("http://localhost:8080"));

		System.out.println(message1);
	}
}
