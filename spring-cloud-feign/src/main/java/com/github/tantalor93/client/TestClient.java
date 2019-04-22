package com.github.tantalor93.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.okhttp.OkHttpClient;

@FeignClient(
		value = "test-client",
		url = "http://localhost:8080",
		configuration = TestClient.Configuration.class
)
public interface TestClient {

	@RequestMapping(method = RequestMethod.GET, value = "/message")
	String getMessage();

	class Configuration {
		@Bean
		OkHttpClient okHttpClient() {
			return new OkHttpClient();
		}
	}
}
