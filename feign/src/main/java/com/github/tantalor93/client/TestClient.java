package com.github.tantalor93.client;

import java.net.URI;

import feign.Headers;
import feign.RequestLine;

public interface TestClient {

	@RequestLine("GET /message")
	@Headers("Accept: text/plain")
	String getMessage();

	@RequestLine("POST /message")
	@Headers({"Accept: text/plain", "Content-Type: text/plain"})
	String postMessage(String body);

	@RequestLine("GET /message")
	@Headers("Accept: text/plain")
	String getMessage(URI host);
}
