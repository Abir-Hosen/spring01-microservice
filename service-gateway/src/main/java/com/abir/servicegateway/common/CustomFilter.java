package com.abir.servicegateway.common;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

	private final WebClient.Builder webClientBuilder;

	public CustomFilter(WebClient.Builder webClientBuilder) {
		super(Config.class);
		System.out.println("inside filter 1 ");
		this.webClientBuilder = webClientBuilder;
	}

	@Override
	public GatewayFilter apply(Config config) {

		return (exchange, chain) -> {
			if (exchange.getRequest().getURI().getPath().equals("/user/api/user")
					|| exchange.getRequest().getURI().getPath().equals("/user/api/user/signin")) {
				return chain.filter(exchange);
			}

			if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				throw new RuntimeException("Missing authorization information");
			}

			String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

			String[] parts = authHeader.split(" ");

			if (parts.length != 2 || !"Bearer".equals(parts[0])) {

				System.out.println("inside filter 4 ");
				throw new RuntimeException("Incorrect authorization structure");
			}

			return webClientBuilder.build().post()
					.uri("http://USER-SERVICE/user/api/user/validate-token?token=" + parts[1]).retrieve()
					.bodyToMono(UserSignInResponse.class).map(UserSignInResponse -> {
						exchange.getRequest().mutate().header("X-auth-user-id",
								String.valueOf(UserSignInResponse.getId()));
						return exchange;
					}).flatMap(chain::filter);
		};
	}

	public static class Config {

	}

}
