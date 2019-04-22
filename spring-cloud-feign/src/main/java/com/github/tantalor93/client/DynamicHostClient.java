package com.github.tantalor93.client;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.okhttp.OkHttpClient;

@FeignClient(
		value = "dynamic-host-client",
		configuration = TestClient.Configuration.class
)
public interface DynamicHostClient {

	@RequestMapping(method = RequestMethod.GET, value = "/message")
	String getMessage(URI host);

	class Configuration {
		@Bean
		OkHttpClient okHttpClient() {
			return new OkHttpClient();
		}
	}
}
