package com.github.tantalor93;

import java.net.URI;

import com.github.tantalor93.client.TestClient;

import feign.Feign;
import feign.Logger;
import feign.Target;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class Application {

	public static void main(String[] args) {
		TestClient client = Feign.builder()
				.client(new OkHttpClient())
				.logger(new Slf4jLogger())
				.logLevel(Logger.Level.FULL)
				.target(TestClient.class, "http://localhost:8080");

		String message = client.getMessage();

		System.out.println(message);

		String res = client.postMessage("ahoj berry");
		System.out.println(res);

		TestClient testClient = Feign.builder()
				.client(new OkHttpClient())
				.logger(new Slf4jLogger())
				.logLevel(Logger.Level.FULL)
				.target(Target.EmptyTarget.create(TestClient.class));
		String message1 = testClient.getMessage(URI.create("http://localhost:8080"));

		System.out.println(message1);
	}
}
