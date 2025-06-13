package com.demo.gatewey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateweyApplication {
	public static void main(String[] args) {
		SpringApplication.run(GateweyApplication.class, args);
	}
	@Bean
	public HttpClient reactorHttpClient() {
		return HttpClient.create()
				.resolver(DefaultAddressResolverGroup.INSTANCE);
	}

	@Bean
	public WebClient webClient(HttpClient reactorHttpClient) {
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(reactorHttpClient))
				.build();
	}

}
