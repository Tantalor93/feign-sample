package com.github.tantalor93.client


import com.github.tomakehurst.wiremock.junit.WireMockRule
import feign.Feign
import feign.Logger
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger
import org.junit.Rule
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

class TestClientTest extends Specification {

    @Rule
    WireMockRule wiremock = new WireMockRule(options().dynamicPort())


    def "should be able to get message"() {
        given:
        stubFor(
                get(urlEqualTo("/message")).willReturn(
                        aResponse().withStatus(200).withBody("Hello world2!")
                )
        )

        TestClient client = Feign.builder()
                .client(new OkHttpClient())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(TestClient.class, "http://localhost:${wiremock.port()}")

        when:
        def message = client.getMessage()

        then:
        message == "Hello world2!"
    }
}
